import com.yorker.model.Artical;
import com.yorker.dao.ListArticals;
import org.junit.Test;

import java.io.File;

/**
 * Created by gyk on 2016/9/22.
 */
public class ListArticalsTest {
//    @Test
//    public void getArticalNames() throws Exception {
//        String base = "E:\\Demo\\java-web\\myblog\\resources\\";
//        ListArticals la = new ListArticals(base);
//        String[] names = la.getArticalNames();
//        for (int i=0;i<names.length;i++){
//            System.out.println(names[i]);
//        }
//    }

    @Test
    public void getLastArtical() throws Exception {
        String base = "E:\\Demo\\java-web\\myblog\\resources\\";
        ListArticals la = new ListArticals(base);
        String s = la.getLastArtical();
        System.out.println(s);
    }

    @Test
    public void sortNames() throws Exception {
        String base = "E:\\Demo\\java-web\\myblog\\resources\\";
        ListArticals la = new ListArticals(base);
        String[] names = la.getArticalNames();
        for (int i = 0; i < names.length; i++) {
            System.out.println(names[i]);
        }
    }

    @Test
    public void getHeaderWithIntroduction() throws Exception {
        String base = "E:\\Demo\\java-web\\myblog\\resources\\";
        ListArticals la = new ListArticals(base);

        String[] names = la.getArticalNames();
        Artical a = la.getHeaderWithIntroduction(names[1]);
        System.out.println(a.getTitle()+"|"+a.getLikes()+"|"+a.getCreateDate().toString()+"|"+a.getContent());
    }

    @Test
    public void getPath(){
        System.out.println(new File("/").getAbsoluteFile());

    }
}
