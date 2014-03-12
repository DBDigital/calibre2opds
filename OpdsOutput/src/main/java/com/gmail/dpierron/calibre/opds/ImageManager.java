package com.gmail.dpierron.calibre.opds;
/**
 *
 */

import com.gmail.dpierron.calibre.cache.CachedFile;
import com.gmail.dpierron.calibre.cache.CachedFileManager;
import com.gmail.dpierron.calibre.configuration.ConfigurationManager;
import com.gmail.dpierron.calibre.datamodel.Book;
import com.gmail.dpierron.calibre.opds.i18n.Localization;
import com.gmail.dpierron.calibre.thumbnails.CreateThumbnail;
import com.gmail.dpierron.tools.Helper;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


public abstract class ImageManager {
  private final static Logger logger = Logger.getLogger(ImageManager.class);

  private boolean imageSizeChanged = false;
  private Map<File, File> generatedImages;

  private int imageHeight = 1;
  private long timeInImages;

  abstract String getResizedFilename();
  abstract String getResizedFilenameOld(Book book);
  abstract String getDefaultResizedFilename();

  private static int countOfImagesGenerated;

  abstract String getImageHeightDat();

  // CONSTRUCTOR

  ImageManager(int imageHeight) {
    reset();
    this.imageHeight = imageHeight;

    CachedFile imageSizeFile = CachedFileManager.INSTANCE.addCachedFile(ConfigurationManager.INSTANCE.getCurrentProfile().getDatabaseFolder(), getImageHeightDat());
    FeedHelper.checkFileNameIsNewStandard(imageSizeFile, CachedFileManager.INSTANCE.addCachedFile(ConfigurationManager.INSTANCE.getCurrentProfile().getDatabaseFolder(), imageSizeFile.getName().substring(4)));

    if (!imageSizeFile.exists())
      imageSizeChanged = true;
    else {
      try {
        ObjectInputStream ois = null;
        try {
          ois = new ObjectInputStream(new FileInputStream(imageSizeFile));
          int oldSize = ois.readInt();
          imageSizeChanged = oldSize != imageHeight;
        } finally {
          if (ois != null)
            ois.close();
          // TODO Need to update cachedFile information.
        }
      } catch (IOException e) {
        // we don't care about the file error, let's just say size has changed
        imageSizeChanged = true;
      }
    }
  }

  // METHODS and PROPERTIES

  public void reset () {
    generatedImages = new HashMap<File, File>();
    countOfImagesGenerated = 0;
    timeInImages = 0;
  }

  public final static ThumbnailManager newThumbnailManager() {
    return new ThumbnailManager(ConfigurationManager.INSTANCE.getCurrentProfile().getThumbnailHeight());
  }

  public final static ImageManager newCoverManager() {
    return new CoverManager(ConfigurationManager.INSTANCE.getCurrentProfile().getCoverHeight());
  }

  public void setImageToGenerate(CachedFile resizedCoverFile, CachedFile coverFile) {
    if (!generatedImages.containsKey(resizedCoverFile)) {
      generateImage(coverFile,resizedCoverFile);
      generatedImages.put(resizedCoverFile, coverFile);
    }
  }


  boolean hasImageSizeChanged() {
    return imageSizeChanged;
  }

  /**
   * Get the URI for a cover image
   * (on the assumption it is in the library)
   *
   * @param book
   * @return
   */
  String getImageUri(Book book) {
    String uriBase = ConfigurationManager.INSTANCE.getCurrentProfile().getUrlBooks();
    if (Helper.isNullOrEmpty(uriBase)) {
      uriBase = Constants.LIBRARY_PATH_PREFIX;
    }
    return uriBase + FeedHelper.urlEncode(book.getPath() + "/" + getResizedFilename(), true);
  }

  public void writeImageHeightFile() {
    File imageSizeFile = new File(ConfigurationManager.INSTANCE.getCurrentProfile().getDatabaseFolder(), getImageHeightDat());
    try {
      ObjectOutputStream oos = null;
      try {
        oos = new ObjectOutputStream(new FileOutputStream(imageSizeFile));
        oos.writeInt(imageHeight);
      } finally {
        if (oos != null)
          oos.close();
      }
    } catch (IOException e) {
      // we don't care if the image height file cannot be written, image will be recomputed and that's all
    }
  }

  /**
   * Get the contents of the specified file as base64 string
   * so that we can embed the image in the xml/html file
   *
   * @return
   */
  public String getFileToBase64Uri (File f) {
    final String base64code = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        + "abcdefghijklmnopqrstuvwxyz" + "0123456789" + "+/";

    int length = (int)f.length();
    int paddingCount = (3 - (length % 3)) % 3;

    byte[] data = new byte[length + 2];
    // Preset potential padding bytes to null
    for (int i = 0 ; i < 2; i++) {
      data[length+(i)] = 0;
    }
    try {
      DataInputStream in = new DataInputStream(new FileInputStream(f));
      in.readFully(data,0,length);
      in.close();
    } catch (EOFException eof) {
      // Ignore this error as we got all the data!
      // However this is still unexpected so lets log it when tracing
      if (logger.isTraceEnabled())  logger.trace("Unexpected EOF reading " + f);
    } catch (IOException e) {
      // Errors are unexpected - set dummy URI if that is the case
      logger.warn("Unable to embed cover file " + f.getAbsolutePath() + "(IO Exception " + e.getMessage() + ")");
      return Constants.PARENT_PATH_PREFIX + Constants.DEFAULT_IMAGE_FILENAME;
    }
    // process 3 bytes at a time, churning out 4 output bytes
    StringBuffer encoded = new StringBuffer("");
    for (int i = 0; i < length; i += 3) {
      int j = ((data[i] & 0xff) << 16) +
              ((data[i + 1] & 0xff) << 8) +
               (data[i + 2] & 0xff);
      encoded = encoded.append("" + base64code.charAt((j >> 18) & 0x3f) +
                               "" + base64code.charAt((j >> 12) & 0x3f) +
                               "" + base64code.charAt((j >> 6) & 0x3f) +
                               "" + base64code.charAt(j & 0x3f));
    }
    data = null;        // <ay not be necessary - but lets help garbage collector
    // Add final padding characters
    for (int i = paddingCount ; i > 0 ; i--) {
      encoded.setCharAt(encoded.length() - i, '=');
    }
    return "data:image/"
            // take image type from file extenson
            + f.getName().substring(f.getName().lastIndexOf('.')+1) + ";base64,"
            + encoded.toString();
  }

  /**
   * generate a single image file
   * @return
   */
  public void generateImage(CachedFile imageFile, File coverFile) {
    logger.debug("generateImage: " + imageFile.getAbsolutePath());
    long now = System.currentTimeMillis();
    try {
      CreateThumbnail ct = new CreateThumbnail(coverFile.getAbsolutePath());
      ct.getThumbnail(imageHeight, CreateThumbnail.VERTICAL);
      ct.saveThumbnail(imageFile, CreateThumbnail.IMAGE_JPEG);
      // bug #732821 Ensure file added to those cached for copying
      CachedFile cf = CachedFileManager.INSTANCE.addCachedFile(imageFile);
      if (logger.isTraceEnabled())
        logger.trace("generateImages: added new thumbnail file " + imageFile.getAbsolutePath() + " to list of files to copy");
      countOfImagesGenerated++;         // Update count of files processed
    } catch (Exception e) {
      CatalogContext.INSTANCE.callback
          .errorOccured(Localization.Main.getText("error.generatingThumbnail", coverFile.getAbsolutePath()), e);
    } catch (Throwable t) {
         logger.warn("Unexpected error trying to generate image " + coverFile.getAbsolutePath() + "\n" + t );
    }
    timeInImages += (System.currentTimeMillis() - now);
    imageFile.clearCachedInformation();
  }

  public long getTimeInImages() {
    return timeInImages;
  }

  public int getCountOfImagesGenerated() {
    return countOfImagesGenerated;
  }

}
