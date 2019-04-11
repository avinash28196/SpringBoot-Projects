package com.example.jpa.model;

import java.util.Date;
import org.springframework.data.annotation.CreatedDate;
import javax.persistence.*;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="articles")
public class Article {

		
		@Id
	    @ApiModelProperty(notes = "The database generated product ID")
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private String articleID;
		
		@Column(name="ARTICLEWORDCOUNT")
		private Long articleWordCount;
		
		
	    @Size(max = 1000)
	    @Column(unique = true)
	    private String headline;
		
		private String category;
		
		@Temporal(TemporalType.TIMESTAMP)
	    @Column(name = "PUBDATE",  updatable = false)
	    @CreatedDate
	    
	    private Date pubDate;
		
		@Lob
		private String snippet;
		
		@Lob
		private String webURL;

		public String getArticleID() {
			return articleID;
		}

		public void setArticleID(String articleID) {
			this.articleID = articleID;
		}

		public Long getArticleWordCount() {
			return articleWordCount;
		}

		public void setArticleWordCount(Long articleWordCount) {
			this.articleWordCount = articleWordCount;
		}

		public String getHeadline() {
			return headline;
		}

		public void setHeadline(String headline) {
			this.headline = headline;
		}

		public String getCategory() {
			return category;
		}

		public void setCategory(String catagory) {
			this.category = catagory;
		}

		public Date getPubDate() {
			return pubDate;
		}

		public void setPubDate(Date pubDate) {
			this.pubDate = pubDate;
		}

		public String getSnippet() {
			return snippet;
		}

		public void setSnippet(String snippet) {
			this.snippet = snippet;
		}

		public String getWebURL() {
			return webURL;
		}

		public void setWebURL(String webURL) {
			this.webURL = webURL;
		}

		@Override
		public String toString() {
			return "Articles [articleID=" + articleID + ", articleWordCount=" + articleWordCount + ", headline="
					+ headline + ", category=" + category + ", pubDate=" + pubDate + ", snippet=" + snippet
					+ ", webURL=" + webURL + "]";
		}
		
		
		
		
		
	
		
		
		
		
		
		
	
}
