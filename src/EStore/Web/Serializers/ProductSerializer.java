package EStore.Web.Serializers;

import java.lang.reflect.Type;

import EStore.Web.Model.Category;
import EStore.Web.Model.Product;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class ProductSerializer  implements JsonSerializer<Product>{

	@Override
	public JsonElement serialize(Product arg0, Type arg1,
			JsonSerializationContext arg2) {
		JsonObject obj = new JsonObject();
	    obj.addProperty("id", arg0.getId());
	    obj.addProperty("description", arg0.getDescription());
	    obj.addProperty("price", arg0.getPrice());
	    obj.addProperty("name", arg0.getName());
	    return obj;
	}

}
