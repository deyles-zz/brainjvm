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

import java.util.ArrayList;
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
public class BrainfuckTokenManagerTest {
    
    public BrainfuckTokenManagerTest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
        
    }
    
    @Before
    public void setUp() {
        
    }
    
    @After
    public void tearDown() {
        
    }

    /**
     * Test of consume method, of class BrainfuckTokenManager.
     */
    @Test
    public void testConsume() {
        System.out.println("consume");
        char t = BrainfuckToken.TOKEN_POINTER_ADVANCE;
        BrainfuckTokenManager instance = new BrainfuckTokenManager();
        instance.consume(t);
        ArrayList<BrainfuckToken> tokens = instance.getTokens();
        assertEquals(tokens.size(), 1);
        assertEquals(tokens.get(0).getToken(), BrainfuckToken.TOKEN_POINTER_ADVANCE);
    }

    /**
     * Test of hasNext method, of class BrainfuckTokenManager.
     */
    @Test
    public void testHasNext() {
        System.out.println("hasNext");
        char t = BrainfuckToken.TOKEN_POINTER_ADVANCE;
        BrainfuckTokenManager instance = new BrainfuckTokenManager();
        instance.consume(t);
        assertEquals(instance.hasNext(), true);
        BrainfuckToken token = instance.next();
        assertTrue(BrainfuckToken.class.isInstance(token));
        assertEquals(token.getToken(), BrainfuckToken.TOKEN_POINTER_ADVANCE);
    }

    /**
     * Test of hasPrevious method, of class BrainfuckTokenManager.
     */
    @Test
    public void testHasPrevious() {
        System.out.println("hasPrevious");
        BrainfuckTokenManager instance = new BrainfuckTokenManager();
        instance.consume(BrainfuckToken.TOKEN_POINTER_ADVANCE); // >
        instance.consume(BrainfuckToken.TOKEN_HEAP_INCREMENT);  // +
        instance.consume(BrainfuckToken.TOKEN_HEAP_DECREMENT);  // -
        instance.next(); // 0 -> 1
        instance.next(); // 1 -> 2
        instance.next(); // 2 -> 3
        assertEquals(instance.hasPrevious(), true); // true
        BrainfuckToken token = instance.previous(); // 2 <- 3
        assertTrue(BrainfuckToken.class.isInstance(token));
        System.out.println(token.getToken());
        assertEquals(token.getToken(), BrainfuckToken.TOKEN_HEAP_DECREMENT);
    }

    /**
     * Test of next method, of class BrainfuckTokenManager.
     */
    @Test
    public void testNext() {
        System.out.println("next");
        char t = BrainfuckToken.TOKEN_POINTER_ADVANCE;
        BrainfuckTokenManager instance = new BrainfuckTokenManager();
        instance.consume(t);
        assertEquals(instance.hasNext(), true);
        BrainfuckToken token = instance.next();
        assertTrue(BrainfuckToken.class.isInstance(token));
        assertEquals(token.getToken(), BrainfuckToken.TOKEN_POINTER_ADVANCE);
    }

    /**
     * Test of nextIndex method, of class BrainfuckTokenManager.
     */
    @Test
    public void testNextIndex() {
        System.out.println("nextIndex");
        BrainfuckTokenManager instance = new BrainfuckTokenManager();
        instance.consume(BrainfuckToken.TOKEN_POINTER_ADVANCE); // >
        instance.consume(BrainfuckToken.TOKEN_HEAP_INCREMENT);  // +
        instance.consume(BrainfuckToken.TOKEN_HEAP_DECREMENT);  // -
        instance.next(); // 0 -> 1
        instance.next(); // 1 -> 2
        instance.next(); // 2 -> 3
        assertEquals(instance.nextIndex(), 3);
    }

    /**
     * Test of previous method, of class BrainfuckTokenManager.
     */
    @Test
    public void testPrevious() {
        System.out.println("previous");
        BrainfuckTokenManager instance = new BrainfuckTokenManager();
        instance.consume(BrainfuckToken.TOKEN_POINTER_ADVANCE); // >
        instance.consume(BrainfuckToken.TOKEN_HEAP_INCREMENT);  // +
        instance.consume(BrainfuckToken.TOKEN_HEAP_DECREMENT);  // -
        instance.next(); // 0 -> 1
        instance.next(); // 1 -> 2
        instance.next(); // 2 -> 3
        BrainfuckToken token = instance.previous();
        assertEquals(token.getToken(), BrainfuckToken.TOKEN_HEAP_DECREMENT);
    }

    /**
     * Test of previousIndex method, of class BrainfuckTokenManager.
     */
    @Test
    public void testPreviousIndex() {
        System.out.println("previousIndex");
        BrainfuckTokenManager instance = new BrainfuckTokenManager();
        instance.consume(BrainfuckToken.TOKEN_POINTER_ADVANCE); // >
        instance.consume(BrainfuckToken.TOKEN_HEAP_INCREMENT);  // +
        instance.consume(BrainfuckToken.TOKEN_HEAP_DECREMENT);  // -
        instance.next(); // 0 -> 1
        instance.next(); // 1 -> 2
        instance.next(); // 2 -> 3
        assertEquals(instance.previousIndex(), 2);
    }

}