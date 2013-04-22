package edu.app.web.mb;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import edu.app.business.CatalogServiceLocal;
import edu.app.persistence.Category;
import edu.app.persistence.Product;

@ManagedBean
@ViewScoped
public class ProductBean {
	
	@EJB
	private CatalogServiceLocal catalogServiceLocal;
	
	private Product product = new Product();
	private List<Product> products;
	private boolean formDisplayed = false;
	private List<SelectItem> selectItemsForCategories;
	private int selectedCategoryId = -1;
	
	
	public ProductBean() {
	}
	
	@PostConstruct
	public void init(){
		products = catalogServiceLocal.findAllProducts();
		List<Category> categories = catalogServiceLocal.findAllCategories();
		selectItemsForCategories = new ArrayList<SelectItem>(categories.size());
		for(Category category:categories){
			selectItemsForCategories.add(new SelectItem(category.getId(),category.getName()));
		}
	}
	
	
	public String doSaveOrUpdate(){
		String navigateTo = null;
		product.setCategory(catalogServiceLocal.findCategoryById(selectedCategoryId));
		catalogServiceLocal.saveOrUpdateProduct(product);
		products = catalogServiceLocal.findAllProducts();
		formDisplayed = false;
		return navigateTo;
	}
	
	public String doNew(){
		String navigateTo = null;
		selectedCategoryId = -1;
		formDisplayed = true;
		return navigateTo;
	}
	
	public String doCancel(){
		String navigateTo = null;
		product = new Product();
		products = catalogServiceLocal.findAllProducts();
		formDisplayed = false;
		return navigateTo;
	}
	
	public String doDelete(){
		String navigateTo = null;
		catalogServiceLocal.removeProduct(product);
		products = catalogServiceLocal.findAllProducts();
		formDisplayed = false;
		return navigateTo;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public boolean isFormDisplayed() {
		return formDisplayed;
	}

	public void setFormDisplayed(boolean formDisplayed) {
		this.formDisplayed = formDisplayed;
	}

	public List<SelectItem> getSelectItemsForCategories() {
		return selectItemsForCategories;
	}

	public void setSelectItemsForCategories(
			List<SelectItem> selectItemsForCategories) {
		this.selectItemsForCategories = selectItemsForCategories;
	}

	public int getSelectedCategoryId() {
		return selectedCategoryId;
	}

	public void setSelectedCategoryId(int selectedCategoryId) {
		this.selectedCategoryId = selectedCategoryId;
	}
	
	

	
	
}
