import com.fp.fourBoxViewer.Entity.Item
import com.fp.fourBoxViewer.Manager.ItemManager
import spock.lang.Specification

/**
 * Created by jlarrieux on 5/16/2017.
 */
class ItemTest extends Specification{

    def "initial size should be 0"(){
        given: "an ItemManager"
        ItemManager manager = new ItemManager();

        when: "no items added yet"

        then: "size should be 0"
        manager.size()==0
    }




    def "add an item and expect size  to be 1"(){
        given: "an Item and ItemManager"
        Item item = new Item()
        ItemManager manager = new ItemManager()

        when: "I add Item to ItemManager"
        manager.add(item)

        then: "count should be 1"
        assert manager.size() ==1
    }
}
