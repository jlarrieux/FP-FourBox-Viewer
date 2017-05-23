import com.fp.fourBoxViewer.Boundaries.HibernateManager
import org.hibernate.Session
import spock.lang.Ignore
import spock.lang.Specification
/**
 * Created by jlarrieux on 5/16/2017.
 */
class HibernateTest  extends  Specification{


    @Ignore
    def "connection is valid"(){
        given: "a valid hibernateManager and session"
        HibernateManager manager = new HibernateManager();


        when: "Session is open"
        Session session = manager.getSession()


        then:"session is valid"
        session.isConnected()  ==true

        cleanup: "we need to close connection"
        manager.exit()


    }
}
