package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.List;

import com.example.demo.models.Categories;
import com.example.demo.repositories.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class DemoApplicationTests {
	private final CategorieRepository categorierepo;

	private final ProduitRepository produitr;

	@Autowired
	public  DemoApplicationTests(CategorieRepository categorierepo,ProduitRepository produitr){
		this.categorierepo=categorierepo;
		this.produitr=produitr;

	}
	@Test
	void contextLoads() {
	}
	@Test
	public void testCreateCategorie(){
		Timestamp t=new Timestamp(System.currentTimeMillis());

		Categories c1= new Categories(1L,"test2",50,t,t,null);

		categorierepo.save(c1);
		assertThat(categorierepo.save(c1)).isNotNull();

	}
	@Test
	public void getAllcategoriesTest(){
		List<Categories>list=(List<Categories>) categorierepo.findAll();
		assertThat(list).size().isGreaterThan(0);
		System.out.println("list %d:"+list.size());

	}
	@Test
	void singleCategory () {
		Categories category  = categorierepo.findById(1L).get() ;
		assertEquals(100 , category.getQT());

	}

	/*@Test
	public String getCategorieById( ) {
		long idcategorie = 2;

			return categorierepo.findById(idcategorie).toString();

	}
*/



}
