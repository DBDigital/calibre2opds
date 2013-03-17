package com.gmail.dpierron.calibre.configuration;

import com.gmail.dpierron.calibre.opds.i18n.Localization;
import com.gmail.dpierron.calibre.opds.indexer.Index;
import com.gmail.dpierron.tools.Composite;

import java.io.File;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

public class StanzaDefaultConfiguration extends DefaultConfiguration implements ReadOnlyStanzaConfigurationInterface {

  public String getLanguage() {
    return Locale.getDefault().getLanguage();
  }

  public String getCatalogFolderName() {
    return StanzaConstants.CATALOGFOLDER;
  }

  public String getCatalogTitle() {
    if (Localization.Main.isInitialized())
      return Localization.Main.getText("main.title");
    else
      return StanzaConstants.CATALOGTITLE;
  }

  public int getMaxBeforePaginate() {
    return StanzaConstants.MAX_BEFORE_PAGINATE;
  }

  public int getBooksInRecentAdditions() {
    return StanzaConstants.MAX_RECENT_ADDITIONS;
  }

  public String getWikipediaLanguage() {
    return StanzaConstants.WIKIPEDIA_LANGUAGE;
  }

  public String getIncludedFormatsList() {
    return StanzaConstants.INCLUDEDFORMATS;
  }

  public boolean getMinimizeChangedFiles() {
    return StanzaConstants.MINIMIZECHANGEDFILES;
  }

  public boolean getExternalIcons() {
    return StanzaConstants.EXTERNALICONS;
  }

  public boolean getSaveBandwidth() {
    return StanzaConstants.SAVEBANDWIDTH;
  }


  public boolean getCoverResize() {
    return StanzaConstants.COVER_RESIZE;
  }

  public boolean getThumbnailGenerate() {
    return StanzaConstants.THUMBNAIL_GENERATE;
  }

  public int getThumbnailHeight() {
    return StanzaConstants.THUMBNAIL_HEIGHT;
  }


  public boolean getGenerateOpds() {
    return StanzaConstants.GENERATEOPDS;
  }

  public boolean getGenerateHtml() {
    return StanzaConstants.GENERATEHTML;
  }

  public boolean getGenerateOpdsDownloads() {
    return StanzaConstants.GENERATEOPDSDOWNLOADS;
  }

  public boolean getGenerateHtmlDownloads() {
    return StanzaConstants.GENERATEHTMLDOWNLOADS;
  }

  public boolean getSuppressRatingsInTitles() {
    return StanzaConstants.suppressRatingsInTitles;
  }

  public boolean getGenerateDownloads() {
    return StanzaConstants.GENERATEDOWNLOADS;
  }

  public int getMaxBeforeSplit() {
    return StanzaConstants.MAX_BEFORE_SPLIT;
  }

  public int getMaxSplitLevels() {
    return StanzaConstants.MAX_SPLIT_LEVELS;
  }

  public String getSplitTagsOn() {
    return StanzaConstants.SPLITTAGSON;
  }

  public boolean getIncludeBooksWithNoFile() {
    return StanzaConstants.INCLUDEBOOKSWITHNOFILE;
  }

  public boolean getCryptFilenames() {
    return StanzaConstants.CRYPT_FILENAMES;
  }

  public boolean getShowSeriesInAuthorCatalog() {
    return StanzaConstants.SHOWSERIESINAUTHORCATALOG;
  }

  public boolean getGenerateCrossLinks() {
    return StanzaConstants.generateCrossLinks;
  }

  public boolean getGenerateExternalLinks() {
    return StanzaConstants.generateExternalLinks;
  }

  public String getCatalogFilter() {
    return StanzaConstants.CatalogFilter;
  }

  public int getMaxSummaryLength() {
    return StanzaConstants.MAX_SUMMARY_LENGTH;
  }

  public int getMaxBookSummaryLength() {
    return StanzaConstants.MAX_BOOK_SUMMARY_LENGTH;
  }

  public boolean getGenerateAuthors() {
    return StanzaConstants.generateAuthors;
  }

  public boolean getGenerateTags() {
    return StanzaConstants.generateTags;
  }

  public boolean getGenerateSeries() {
    return StanzaConstants.generateSeries;
  }

  public boolean getGenerateRecent() {
    return StanzaConstants.generateRecent;
  }

  public boolean getGenerateRatings() {
    return StanzaConstants.generateRatings;
  }

  public boolean getGenerateAllbooks() {
    return StanzaConstants.generateAllbooks;
  }

  public File getTargetFolder() {
    return new File(StanzaConstants.targetFolder);
  }

  public DeviceMode getDeviceMode() {
    return DeviceMode.Dropbox;
  }

  public boolean getCopyToDatabaseFolder() {
    return StanzaConstants.COPYTODATABASEFOLDER;
  }

  public boolean getBrowseByCover() {
    return StanzaConstants.browseByCover;
  }

  public boolean getSplitByAuthorInitialGoToBooks() {
    return StanzaConstants.splitByAuthorInitialGoToBooks;
  }

  public boolean getIncludeAboutLink() {
    return StanzaConstants.includeAboutLink;
  }

  public boolean getPublishedDateAsYear() {
    return StanzaConstants.publishedDateAsYear;
  }

  public String getTagsToMakeDeep() {
    return StanzaConstants.tagsToMakeDeep;
  }

  public boolean getBrowseByCoverWithoutSplit() {
    return StanzaConstants.browseByCoverWithoutSplit;
  }

  public int getMinBooksToMakeDeepLevel() {
    return StanzaConstants.minBooksToMakeDeepLevel;
  }

  public int getCoverHeight() {
    return StanzaConstants.CoverHeight;
  }

  public int getMaxMobileResolution() {
    return StanzaConstants.MAX_MOBILE_RESOLUTION;
  }

  public boolean getIncludeOnlyOneFile() {
    return StanzaConstants.IncludeOnlyOneFile;
  }

  public boolean getZipTrookCatalog() {
    return StanzaConstants.ZipTrookCatalog;
  }

  public boolean getReprocessEpubMetadata() {
    return StanzaConstants.ReprocessEpubMetadata;
  }

  public boolean getOrderAllBooksBySeries() {
    return StanzaConstants.OrderAllBooksBySeries;
  }

  public String getAmazonAuthorUrl() {
    return Localization.Main.getText(StanzaConstants.AMAZON_AUTHORS_URL_DEFAULT);
  }

  public String getAmazonIsbnUrl() {
    return Localization.Main.getText(StanzaConstants.AMAZON_ISBN_URL_DEFAULT);
  }

  public String getAmazonTitleUrl() {
    return Localization.Main.getText(StanzaConstants.AMAZON_TITLE_URL_DEFAULT);
  }

  public String getGoodreadAuthorUrl() {
    return Localization.Main.getText(StanzaConstants.GOODREADS_AUTHOR_URL_DEFAULT);
  }

  public String getGoodreadIsbnUrl() {
    return Localization.Main.getText(StanzaConstants.GOODREADS_ISBN_URL_DEFAULT);
  }

  public String getGoodreadTitleUrl() {
    return Localization.Main.getText(StanzaConstants.GOODREADS_TITLE_URL_DEFAULT);
  }

  public String getGoodreadReviewIsbnUrl() {
    return Localization.Main.getText(StanzaConstants.GOODREADS_REVIEW_URL_DEFAULT);
  }

  public String getIsfdbAuthorUrl() {
    return Localization.Main.getText(StanzaConstants.ISFDB_AUTHOR_URL_DEFAULT);
  }

  public String getLibrarythingAuthorUrl() {
    return Localization.Main.getText(StanzaConstants.LIBRARYTHING_AUTHOR_URL_DEFAULT);
  }

  public String getLibrarythingIsbnUrl() {
    return Localization.Main.getText(StanzaConstants.LIBRARYTHING_ISBN_URL_DEFAULT);
  }

  public String getLibrarythingTitleUrl() {
    return Localization.Main.getText(StanzaConstants.LIBRARYTHING_TITLE_URL_DEFAULT);
  }

  public String getWikipediaUrl() {
    return Localization.Main.getText(StanzaConstants.WIKIPEDIA_URL_DEFAULT);
  }

  public boolean getGenerateIndex() {
    return StanzaConstants.GenerateIndex;
  }

  public boolean getIndexComments() {
    return StanzaConstants.IndexComments;
  }

  public int getMaxKeywords() {
    return StanzaConstants.MaxKeywords;
  }

  public Index.FilterHintType getIndexFilterAlgorithm() {
    return StanzaConstants.IndexFilterAlgorithm;
  }

  public String getUrlBooks() {
    return StanzaConstants.UrlBooks;
  }

  public String getFeaturedCatalogTitle() {
    return StanzaConstants.FeaturedCatalogTitle;
  }

  public String getFeaturedCatalogSavedSearchName() {
    return StanzaConstants.FeaturedCatalogSavedSearchName;
  }

  public List<Composite<String, String>> getCustomCatalogs() {
    return new Vector<Composite<String, String>>();
  }

  /* Catalog Structure */

  public boolean getDisplayAuthorSortInAuthorLists() {
    return StanzaConstants.DisplayAuthorSortInBookDetails;
  }

  public boolean getDisplayTitleSortInBookLists() {
    return StanzaConstants.DisplayTitleSortInBookLists;
  }

  public boolean getSortUsingAuthor() {
    return StanzaConstants.SortUsingAuthor;
  }

  public boolean getSortUsingTitle() {
    return StanzaConstants.SortUsingTitle;
  }

  /* Book Details */

  public boolean getIncludeSeriesInBookDetails() {
    return StanzaConstants.includeSeriesInBookDetails;
  }

  public boolean getIncludeTagsInBookDetails() {
    return StanzaConstants.includeTagsInBookDetails;
  }

  public boolean getIncludePublisherInBookDetails() {
    return StanzaConstants.includePublisherInBookDetails;
  }

  public boolean getIncludePublishedInBookDetails() {
    return StanzaConstants.includePublishedInBookDetails;
  }

  public boolean getIncludeAddedInBookDetails() {
    return StanzaConstants.IncludeAddedInBookDetails;
  }

  public boolean getIncludeModifiedInBookDetails() {
    return StanzaConstants.IncludeModifiedInBookDetails;
  }

  public boolean getDisplayAuthorSortInBookDetails() {
    return StanzaConstants.DisplayAuthorSortInBookDetails;
  }

  public boolean getDisplayTitleSortInBookDetails() {
    return StanzaConstants.DisplayTitleSortInBookDetails;
  }
}
