package com.gmail.dpierron.calibre.database;
/**
 * Abstract the SQL underlying standard requests for calibre2opds
 */
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public enum DatabaseRequest {
  TEST("SELECT COUNT(*) FROM books"),
  ALL_TAGS(
      "SELECT DISTINCT  id, name " +
      " FROM tags " +
      " ORDER BY name "),
  
  ALL_BOOKS(
      "SELECT  DISTINCT   " +
      "b.id AS book_id,   " +
      "b.title AS book_title,   " +
      "b.series_index AS series_index,   " +
      "b.path AS book_path,   " +
      "b.timestamp AS book_timestamp, " +
      "b.pubdate AS book_pubdate, " +
      "b.isbn AS isbn, " +
      "b.uuid AS uuid, " +
      "b.author_sort AS author_sort, " +
      "r.rating AS rating " +
      "FROM books b " +
      "LEFT OUTER JOIN books_ratings_link brl ON brl.book=b.id " +
      "LEFT OUTER JOIN ratings r ON brl.rating=r.id "),
      
  ALL_AUTHORS(
      "select " +
        "a.id, " + 
    		"a.name, " + 
    		"a.sort " + 
  		"from authors a " + 
  		"order by a.id" ),
  
  ALL_PUBLISHERS(
      "select " +
        "p.id, " + 
        "p.name, " + 
        "p.sort " + 
      "from publishers p " + 
      "order by p.id" ),
  	          
  ALL_SERIES("select " +
    		"s.id, " + 
    		"s.name, " + 
    		"s.sort as serie_sort " + 
  		"from series s " + 
  		"order by s.sort"),
  		
  BOOKS_SERIES("select book, series from books_series_link"),
  BOOKS_TAGS("select book, tag from books_tags_link"),
  BOOKS_AUTHORS("select book, author from books_authors_link"),
  BOOKS_PUBLISHERS("select book, publisher from books_publishers_link"),
  BOOKS_DATA("select book, format, name from data"),
  BOOKS_COMMENTS("select book, text from comments");
;
  
  private static final Logger logger = Logger.getLogger(DatabaseRequest.class);
  private String sql;
  private PreparedStatement preparedStatement;

  private DatabaseRequest(String sql) {
    this.sql = sql;
  }

  public PreparedStatement getStatement() {
    if (preparedStatement == null) {
      try {
        preparedStatement = DatabaseManager.INSTANCE.getConnection()
            .prepareStatement(sql);
      } catch (SQLException e) {
        logger.error(e);
      }
    }
    return preparedStatement;
  }

}