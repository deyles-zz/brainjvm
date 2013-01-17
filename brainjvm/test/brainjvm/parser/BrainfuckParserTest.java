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
public class BrainfuckParserTest {
    
    public BrainfuckParserTest() {
        
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
     * Test of parse method, of class BrainfuckParser.
     */
    @Test
    public void testParse() throws Exception {
        System.out.println("parse");
        String code = ">>>+.";
        BrainfuckParser instance = new BrainfuckParser();
        BrainfuckTokenManager result = instance.parse(code);
        int count = 0;
        while(result.hasNext()) {
            BrainfuckToken token = result.next();
            switch(count) {
                case 0:
                    assertEquals(token.getToken(), BrainfuckToken.TOKEN_POINTER_ADVANCE);
                    break;
                    
                case 1:
                    assertEquals(token.getToken(), BrainfuckToken.TOKEN_POINTER_ADVANCE);
                    break;
                    
                case 2:
                    assertEquals(token.getToken(), BrainfuckToken.TOKEN_POINTER_ADVANCE);
                    break;
                    
                case 3:
                    assertEquals(token.getToken(), BrainfuckToken.TOKEN_HEAP_INCREMENT);
                    break;
                    
                case 4:
                    assertEquals(token.getToken(), BrainfuckToken.TOKEN_STDOUT_WRITE);
                    break;
            }
            count++;
        }
    }
}
