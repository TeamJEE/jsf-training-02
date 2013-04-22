package edu.app.web.mb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import edu.app.business.CatalogServiceLocal;
import edu.app.persistence.Category;

@ManagedBean
@ViewScoped
public class CategoryBean {
	
	@EJB
	private CatalogServiceLocal catalogServiceLocal;
	
	private Category category = new Category();
	private List<Category> categories;
	
	private boolean formDisplayed = false;
	
	public CategoryBean() {
	}
	
	@PostConstruct
	public void init(){
		categories = catalogServiceLocal.findAllCategories();
	}

	public String doSaveOrUpdate(){
		String navigateTo = null;
		catalogServiceLocal.saveOrUpdateCategory(category);
		categories = catalogServiceLocal.findAllCategories();
		formDisplayed = false;
		return navigateTo;
	}
	
	public String doNew(){
		String navigateTo = null;
		formDisplayed = true;
		return navigateTo;
	}
	
	public String doCancel(){
		String navigateTo = null;
		category = new Category();
		categories = catalogServiceLocal.findAllCategories();
		formDisplayed = false;
		return navigateTo;
	}
	
	public String doDelete(){
		String navigateTo = null;
		catalogServiceLocal.removeCategory(category);
		categories = catalogServiceLocal.findAllCategories();
		formDisplayed = false;
		return navigateTo;
	}
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public boolean isFormDisplayed() {
		return formDisplayed;
	}

	public void setFormDisplayed(boolean formDisplayed) {
		this.formDisplayed = formDisplayed;
	}
	
	
	

}
