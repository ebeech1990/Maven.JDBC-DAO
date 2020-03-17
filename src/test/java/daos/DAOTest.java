package daos;

import models.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DAOTest {
    DAO dao;

    @Before
    public void init(){
        dao = new DAO();
    }

    @Test
    public void findByIdTest(){
        User retrieved = dao.findById(3);
        Integer expected = 3;
        Integer actual = retrieved.getId();
        Assert.assertEquals(expected,actual);
    }

}
