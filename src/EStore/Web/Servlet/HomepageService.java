package EStore.Web.Servlet;

import java.lang.reflect.Type;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import DAL.Framework.UnitOfWork;
import EStore.Web.BusinessLayer.ICategoryService;
import EStore.Web.BusinessLayer.Impl.CategoryServiceImpl;
import EStore.Web.Model.Category;
import EStore.Web.Model.Product;
import EStore.Web.Serializers.CategorySerializer;
import EStore.Web.Serializers.ProductSerializer;

@Path(value="/homepageService")
public class HomepageService {

	//@Autowired
	private ICategoryService categoryService=new CategoryServiceImpl(new UnitOfWork());
	
	@GET
	@Path("getCategories")
	@Produces("application/json")
	public String getCategories(){
		
		List<Category> categories = categoryService.getAllCategories();
		Type type = new TypeToken<List<Category>>(){}.getType();
		Gson gson = new GsonBuilder().registerTypeAdapter(Category.class, new CategorySerializer())
									 .registerTypeAdapter(Product.class, new ProductSerializer())
									 .create();
		String result = gson.toJson(categories);
		return result;
	}
	
}
