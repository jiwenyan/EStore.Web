package DAL.Framework;

import java.util.List;

import javassist.NotFoundException;

public interface IRepository<T extends Entity>{
	
	/**
	 * Create an entity into database
	 * @param toCreate The object to create.
	 * @return The created object id.
	 */
	public T Create(T toCreate);
	
	/**
	 * @return A list of object in database.
	 */
	public List<T> Read();
	
	/**
	 * Read a line from database by the entity id.
	 * @param id The id of the object to read.
	 * @return The found object or null.
	 * @throws NotFoundException 
	 */
	public T ReadById(int id) throws NotFoundException;
	
	/**
	 * Persiste all updates on the object to database.
	 * 
	 * @param toUpdate
	 */
	public void Update(T toUpdate);
	
	/**
	 * Delete the record from database.
	 * @param toDelete the record to delete.
	 * @return True if the delete is correctly done otherwise false.
	 */
	public boolean Delete(T toDelete);
	
	/**
	 * Delete a record by the id of the record.
	 * @param idToDelete Id of the record to delete.
	 * @return True if the delete is correctly done, otherwise false.
	 */
	public boolean Delete(int idToDelete);

}
