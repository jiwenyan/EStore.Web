package EStore.Web.Serializers;

import java.lang.reflect.Type;

import EStore.Web.Model.Category;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class CategorySerializer implements JsonSerializer<Category> {

	@Override
	public JsonElement serialize(Category arg0, Type arg1,
			JsonSerializationContext arg2) {
		JsonObject obj = new JsonObject();
	    obj.addProperty("name", arg0.getName());
	    obj.addProperty("id", arg0.getId());
	    obj.add("products", arg2.serialize(arg0.getProducts()));
	    return obj;
	}

}
