package com.bookcomment.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.bookcomment.entity.BookDetails;

@Mapper
public interface BookDao {


	@Insert("insert into book_details (book_name, book_index, book_author, book_publish_house,book_publish_time, book_abstract,"
			+ "book_category, book_image_url) values(#{bookName}, #{bookIndex},#{bookAuthor}, #{bookPublishHouse},"
			+ "#{bookPublishTime}, #{bookAbstract}, #{bootCategory}, #{bookImageUrl})")
	public void insertBook(BookDetails bookDetails);

	@Select("select * from book_details")
	public List<BookDetails> selectAllbooks();

	@Delete("delete from book_details where book_id = #{id}")
	public void deleteBookById(String id);
}
