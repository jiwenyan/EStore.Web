package EStore.Web.Servlet;

import java.lang.reflect.Type;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import EStore.Web.BusinessLayer.ICategoryService;
import EStore.Web.BusinessLayer.IMarkService;
import EStore.Web.BusinessLayer.IProductService;
import EStore.Web.Model.Category;
import EStore.Web.Model.Mark;
import EStore.Web.Model.Product;
import EStore.Web.Serializers.CategorySerializer;
import EStore.Web.Serializers.ProductSerializer;

@Path(value="/homepageService")
public class HomepageService {

	@Autowired
	private ICategoryService categoryService=(ICategoryService)Bootstrapper.getObject("categoryServiceImpl");
	private IMarkService markService = (IMarkService)Bootstrapper.getObject("markServiceImpl");
	private IProductService productService = (IProductService)Bootstrapper.getObject("productServiceImpl");
	
	@GET
	@Path("getCategories")
	@Produces("application/json")
	public String getCategories(){
		
		List<Category> categories = categoryService.getAllCategories();
		Gson gson = new GsonBuilder().registerTypeAdapter(Category.class, new CategorySerializer())
									 .registerTypeAdapter(Product.class, new ProductSerializer())
									 .create();
		String result = gson.toJson(categories);
		return result;
	}
	
	
	
	@GET
	@Path("getMarks")
	@Produces("application/json")
	public String getMarks(){
		List<Mark> marks = markService.getMarkList();
		JsonSerializer<Mark> serializer = new JsonSerializer<Mark>(){
			@Override
			public JsonElement serialize(Mark arg0, Type arg1,
					JsonSerializationContext arg2) {
				JsonObject obj = new JsonObject();
			    obj.addProperty("id", arg0.getId());
			    obj.addProperty("name", arg0.getName());
			    obj.addProperty("country", arg0.getCountry());
			    obj.addProperty("url", arg0.getUrl());
			    return obj;
			}
		};
		
		Gson gson = new GsonBuilder().registerTypeAdapter(Mark.class, serializer).create();
		
		return gson.toJson(marks);
	}
	
	@GET
	@Path("getProduct")
	@Produces("application/json")
	public String getProducts(){
		List<Product> products = productService.getProductList();
		@SuppressWarnings("rawtypes")
		final JsonSerializer serializer = new JsonSerializer<Product>() {
			@Override
			public JsonElement serialize(Product arg0, Type arg1,
					JsonSerializationContext arg2) {
				JsonObject obj = new JsonObject();
				obj.addProperty("id",arg0.getId());
				obj.addProperty("name",arg0.getName());
				obj.addProperty("price",arg0.getPrice());
				obj.add("mark", arg2.serialize(arg0.getMark()));
				return obj;
			}
		};
		
		@SuppressWarnings("rawtypes")
		final JsonSerializer markSerializer = new JsonSerializer<Mark>() {
			@Override
			public JsonElement serialize(Mark arg0, Type arg1,
					JsonSerializationContext arg2) {
				JsonObject obj = new JsonObject();
			    obj.addProperty("id", arg0.getId());
			    obj.addProperty("name", arg0.getName());
			    obj.addProperty("country", arg0.getCountry());
			    obj.addProperty("url", arg0.getUrl());
			    return obj;
			}
		};
		
		@SuppressWarnings("rawtypes")
		final JsonSerializer categorySerializer = new JsonSerializer<Category>() {
			@Override
			public JsonElement serialize(Category arg0, Type arg1,
					JsonSerializationContext arg2) {
				JsonObject obj = new JsonObject();
				 obj.addProperty("name", arg0.getName());
				 obj.addProperty("id", arg0.getId());
				 return obj;
			}
		};
		
		Gson gson = new GsonBuilder().registerTypeAdapter(Product.class, serializer)
					 				 .registerTypeAdapter(Mark.class, markSerializer)
					 				 .registerTypeAdapter(Category.class, categorySerializer)
					 				 .create();
		return gson.toJson(products);
	}
	
	
}
