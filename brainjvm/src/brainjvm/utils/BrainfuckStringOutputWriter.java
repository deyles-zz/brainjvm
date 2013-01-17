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

package brainjvm.utils;

import java.lang.StringBuffer;

/**
 * Writes output to the StringBuffer instance provided in the constructor
 * @implements IBrainfuckOutputWriter
 * @class BrainfuckFileOutputWriter
 * @author dan
 */
public class BrainfuckStringOutputWriter
        implements IBrainfuckOutputWriter {

    /**
     * @type StringBuffer
     */
    private StringBuffer  buffer;
    
    /**
     * @type boolean
     */
    private boolean raw = false;
    
    /**
     * @constructor
     * @param nfilename the StringBuffer to write output to
     */
    public BrainfuckStringOutputWriter(StringBuffer nbuffer) {
        buffer = nbuffer;
    }
    
    /**
     * Sets a boolean flag indicating whether or not output should be raw bytes
     * or characters
     * @param nraw 
     */
    public void setRaw(boolean nraw) {
        raw = nraw;
    }
    
    /**
     * Write the raw program byte code to provided StringBuffer instance
     * @param program the byte code to write to disk
     * @throws Exception 
     */
    public void write(byte[] program) throws Exception {
        if (raw) {
            buffer.append(new String(program));
        } else {
            buffer.append(new String(program));
        }
    }
    
}