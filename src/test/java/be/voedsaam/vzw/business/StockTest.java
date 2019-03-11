package be.voedsaam.vzw.business;

import be.voedsaam.vzw.enums.ProductType;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StockTest {
    private Stock classUnderTest;

    private Product fruit;
    private Product groente;
    private Product fead;
    private Product meat;

    @Before
    public void Setup(){
        classUnderTest = new Stock();
        fead = new Product();
        fead.setProductType(ProductType.FEAD);
        fead.setName("Magere melk");
        fead.setDescription("verpakt per 6");
        fead.setUnitOfMeasure(new Double(6));

        groente = new Product();
        groente.setProductType(ProductType.VEGETABLES);
        groente.setName("bloemkool");
        groente.setDescription("per stuk");
        groente.setUnitOfMeasure(new Double(0.5));


        fruit = new Product();
        fruit.setProductType(ProductType.FRUIT);
        fruit.setName("Appelen");
        fruit.setDescription("per 4 verpakt");
        fruit.setUnitOfMeasure(new Double(0.9));

        meat = new Product();
        meat.setProductType(ProductType.MEAT);
        meat.setName("hesp");
        meat.setDescription("100 gr per verpakking");
        meat.setUnitOfMeasure(new Double(0.1));
    }

    @Test
    public void getLocation() {
        assertNull(classUnderTest.getLocation());
    }

    @Test
    public void setLocation() {
        classUnderTest.setLocation("Locatie 1");
        assertEquals("Locatie 1", classUnderTest.getLocation());
        classUnderTest.setLocation("Locatie 2");
        assertEquals("Locatie 2", classUnderTest.getLocation());

    }

    @Test
    public void getName() {
        assertNull(classUnderTest.getName());
    }

    @Test
    public void setName() {
        classUnderTest.setName("Locatie 1");
        assertEquals("Locatie 1", classUnderTest.getName());
        classUnderTest.setName("Locatie 2");
        assertEquals("Locatie 2", classUnderTest.getName());

    }

    @Test
    public void addProduct() {
        assertEquals(0,classUnderTest.getProducts().keySet().size());
        classUnderTest.addProduct(groente,10);
        classUnderTest.addProduct(meat,10);
        classUnderTest.addProduct(fruit,10);
        classUnderTest.addProduct(fead,10);
        assertEquals(4,classUnderTest.getProducts().keySet().size());
        assertEquals(10,classUnderTest.getProducts().get(groente),0);
        assertEquals(10,classUnderTest.getProducts().get(meat),0);
        assertEquals(10,classUnderTest.getProducts().get(fruit),0);
        assertEquals(10,classUnderTest.getProducts().get(fead),0);

        assertTrue(fruit.getStocks().contains(classUnderTest));
        assertTrue(meat.getStocks().contains(classUnderTest));
        assertTrue(groente.getStocks().contains(classUnderTest));
        assertTrue(fead.getStocks().contains(classUnderTest));
        assertEquals(1, fead.getStocks().size());

        classUnderTest.addProduct(groente,5);
        classUnderTest.addProduct(meat,5);
        classUnderTest.addProduct(fruit,5);
        classUnderTest.addProduct(fead,5);
        assertEquals(4,classUnderTest.getProducts().keySet().size());
        assertEquals(15,classUnderTest.getProducts().get(groente),0);
        assertEquals(15,classUnderTest.getProducts().get(meat),0);
        assertEquals(15,classUnderTest.getProducts().get(fruit),0);
        assertEquals(15,classUnderTest.getProducts().get(fead),0);
        assertEquals(1, fead.getStocks().size());
    }

    @Test
    public void removeProduct() {
        addProduct();
        classUnderTest.removeProduct(fruit,3);
        classUnderTest.removeProduct(groente,3);
        classUnderTest.removeProduct(meat,3);
        classUnderTest.removeProduct(fead,3);
        assertEquals(4,classUnderTest.getProducts().keySet().size());
        assertEquals(12,classUnderTest.getProducts().get(groente),0);
        assertEquals(12,classUnderTest.getProducts().get(meat),0);
        assertEquals(12,classUnderTest.getProducts().get(fruit),0);
        assertEquals(12,classUnderTest.getProducts().get(fead),0);

        classUnderTest.removeProduct(groente,12);
        assertNull(classUnderTest.getProducts().get(groente));
        assertEquals(3,classUnderTest.getProducts().keySet().size());
        classUnderTest.removeProduct(fruit,20);
        assertNull(classUnderTest.getProducts().get(fruit));
        assertEquals(2,classUnderTest.getProducts().keySet().size());
        classUnderTest.removeProduct(meat,11);
        assertEquals(1,classUnderTest.getProducts().get(meat),0);
        assertEquals(2,classUnderTest.getProducts().keySet().size());

        classUnderTest.removeProduct(fead,2);
        assertEquals(10,classUnderTest.getProducts().get(fead),0);
        assertEquals(2,classUnderTest.getProducts().keySet().size());

    }

    @Test(expected = Exception.class)
    public void getProducts() {
        addProduct();
        classUnderTest.getProducts().put(groente,1000);


    }
}