import com.fp.fourBoxViewer.Entity.Item
import com.fp.fourBoxViewer.Manager.ItemManager
import spock.lang.Specification

/**
 * Created by jlarrieux on 5/16/2017.
 */
class ItemManagerTest extends Specification{

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




    def "item already in Item manager should return true on call to contains method"(){
        given: "an item inserted into an item manager"
        Item item = new Item()
        ItemManager manager = new ItemManager()



        when: "item is added to item manager"
        manager.add(item)


        then: "calling contains with that same item should return true"
        assert  manager.contains(item)


    }

    def "item not added t Item manager should return false on call to contains method"(){
        given: "an item inserted into an item manager"
        Item item = new Item()
        ItemManager manager = new ItemManager()

        expect: "calling contains with that item should return false"
        assert !manager.contains(item)

    }





}
