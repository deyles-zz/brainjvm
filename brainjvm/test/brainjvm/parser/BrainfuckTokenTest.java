/**
 * Copyright (c) 2013, Dan Eyles (dan@irlgaming.com)
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of IRL Gaming nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL IRL Gaming BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package brainjvm.parser;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author dan
 */
public class BrainfuckTokenTest {
    
    private BrainfuckToken prev;
    private BrainfuckToken next;
    
    public BrainfuckTokenTest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {

    }
    
    @AfterClass
    public static void tearDownClass() {
        
    }
    
    @Before
    public void setUp() {
        prev = new BrainfuckToken(BrainfuckToken.TOKEN_POINTER_ADVANCE);
        next = new BrainfuckToken(BrainfuckToken.TOKEN_POINTER_RETREAT);        
    }
    
    @After
    public void tearDown() {
        
    }

    /**
     * Test of determineTypeCode method, of class BrainfuckToken.
     */
    @Test
    public void testDetermineTypeCode() {
        System.out.println("determineTypeCode");
        assertEquals(BrainfuckToken.determineTypeCode('>'), BrainfuckToken.TYPE_POINTER_ADVANCE);
        assertEquals(BrainfuckToken.determineTypeCode('<'), BrainfuckToken.TYPE_POINTER_RETREAT);
        assertEquals(BrainfuckToken.determineTypeCode('+'), BrainfuckToken.TYPE_HEAP_INCREMENT);
        assertEquals(BrainfuckToken.determineTypeCode('-'), BrainfuckToken.TYPE_HEAP_DECREMENT);
        assertEquals(BrainfuckToken.determineTypeCode('.'), BrainfuckToken.TYPE_STDOUT_WRITE);
        assertEquals(BrainfuckToken.determineTypeCode(','), BrainfuckToken.TYPE_STDIN_READ);
        assertEquals(BrainfuckToken.determineTypeCode('['), BrainfuckToken.TYPE_LOOP_OPEN);
        assertEquals(BrainfuckToken.determineTypeCode(']'), BrainfuckToken.TYPE_LOOP_CLOSE);
        assertEquals(BrainfuckToken.determineTypeCode('#'), BrainfuckToken.TYPE_UNKNOWN);
    }

    /**
     * Test of getToken method, of class BrainfuckToken.
     */
    @Test
    public void testGetToken() {
        System.out.println("getToken");
        BrainfuckToken instance = new BrainfuckToken(BrainfuckToken.TOKEN_POINTER_ADVANCE);
        char result = instance.getToken();
        assertEquals(BrainfuckToken.TOKEN_POINTER_ADVANCE, result);
    }

    /**
     * Test of getType method, of class BrainfuckToken.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        BrainfuckToken instance = new BrainfuckToken(BrainfuckToken.TOKEN_POINTER_ADVANCE);
        int type = instance.getType();
        assertEquals(type, BrainfuckToken.TYPE_POINTER_ADVANCE);
    }

    /**
     * Test of getPreviousToken method, of class BrainfuckToken.
     */
    @Test
    public void testGetPreviousToken() {
        System.out.println("getPreviousToken");
        BrainfuckToken instance = new BrainfuckToken(BrainfuckToken.TOKEN_STDOUT_WRITE);
        instance.setPreviousToken(prev);
        assertEquals(prev, instance.getPreviousToken());
        assertEquals(prev.getNextToken(), null);
    }

    /**
     * Test of setPreviousToken method, of class BrainfuckToken.
     */
    @Test
    public void testSetPreviousToken() {
        System.out.println("setPreviousToken");
        BrainfuckToken instance = new BrainfuckToken(BrainfuckToken.TOKEN_STDOUT_WRITE);
        instance.setPreviousToken(prev);
        assertEquals(prev, instance.getPreviousToken());
        assertEquals(prev.getNextToken(), null);
    }

    /**
     * Test of getNextToken method, of class BrainfuckToken.
     */
    @Test
    public void testGetNextToken() {
        System.out.println("getNextToken");
        BrainfuckToken instance = new BrainfuckToken(BrainfuckToken.TOKEN_STDOUT_WRITE);
        instance.setNextToken(next);
        assertEquals(next, instance.getNextToken());
        assertEquals(next.getPreviousToken(), null);
    }

    /**
     * Test of setNextToken method, of class BrainfuckToken.
     */
    @Test
    public void testSetNextToken() {
        System.out.println("setNextToken");
        BrainfuckToken instance = new BrainfuckToken(BrainfuckToken.TOKEN_STDOUT_WRITE);
        instance.setNextToken(next);
        assertEquals(next, instance.getNextToken());
        assertEquals(next.getPreviousToken(), null);
    }
    
}