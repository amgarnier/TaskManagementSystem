package DBModels;

import java.sql.Connection;
import java.util.ArrayList;

/**
 * Table Model -- Brief statement as to file purpose
 * CSIS 212 -- B032023
 * Citations as needed
 */
interface IDatabaseActions<T> {
    Connection connection = null;
    boolean createItem(T value);

    ArrayList retrieveArrayList(Integer taskId);
    T retrieveItem(int noteId);

    boolean updateItem(T value);
    boolean deleteItem(Integer note_id);
}
