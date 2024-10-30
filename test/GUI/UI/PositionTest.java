package GUI.UI;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Larissa Goh 18029695
 * Test class for Position.
 * Verifies that row and column values are correctly set and retrieved.
 */
public class PositionTest {
    
    @Before // Reset position before each test
    public void setUp() {  
        Position.setRow(0);
        Position.setCol(0);
    }
    
    @After // Reset postion after each test
    public void tearDown() {
        Position.setRow(0);
        Position.setCol(0);
    }

    /**
     * Test of getRow method, of class Position.
     * Check that the initial row value is retrieved correctly.
     */
    @Test
    public void testGetRow() {
        System.out.println("Testing getRow");
        int expectedRow = 0;
        int actualRow = Position.getRow();
        assertEquals("Row should be initialized to 0", expectedRow, actualRow);
    }

    /**
     * Test of getCol method, of class Position.
     * Check  that the initial column value is retrieved correctly.
     */
    @Test
    public void testGetCol() {
        System.out.println("Testing getCol");
        int expectedCol = 0;
        int actualCol = Position.getCol();
        assertEquals("Column should be initialized to 0", expectedCol, actualCol);
    }

    /**
     * Test of setRow method, of class Position.
     * Sets and verifies row
     */
    @Test
    public void testSetRow() {
        System.out.println("Testing setRow");
        int newRow = 5;
        Position.setRow(newRow);
        assertEquals("Row should be set to 5", newRow, Position.getRow());
    }

    /**
     * Test of setCol method, of class Position.
     * Sets and verifies column
     */
    @Test
    public void testSetCol() {
        System.out.println("Testing setCol");
        int newCol = 3;
        Position.setCol(newCol);
        assertEquals("Column should be set to 3", newCol, Position.getCol());
    }
}
