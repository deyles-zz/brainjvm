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

package brainjvm.compiler;

import brainjvm.parser.BrainfuckToken;
import brainjvm.parser.BrainfuckTokenManager;
import brainjvm.parser.BrainfuckParser;

import brainjvm.utils.IBrainfuckOutputWriter;
import brainjvm.core.IBrainfuckAssembler;
        
import java.lang.StringBuffer;
import java.util.Stack;

/**
 * Compiles Brainfuck source code to java code
 * @class BrainfuckCompiler
 * @implements IBrainfuckAssembler
 * @author dan
 */
public class BrainfuckCompiler 
implements IBrainfuckAssembler {

    private IBrainfuckOutputWriter writer;
    
    public BrainfuckCompiler() {
        
    }
    
    public void setOutputWriter(IBrainfuckOutputWriter nwriter) {
        writer = nwriter;
    }
    
    public void compile(String filename, String code) throws Exception {
        StringBuffer          buffer = new StringBuffer();
        BrainfuckParser       parser = new BrainfuckParser();
        BrainfuckTokenManager manager = parser.parse(code);
        BrainfuckToken        token;        
        
        header(filename, buffer);
                
        int i = 2;
        while(manager.hasNext()) {
            indent(buffer, i);
            token = manager.next();
            switch(token.getType()) {
                case BrainfuckToken.TYPE_STDIN_READ:
                    buffer.append("stdin();\n");
                    break;
                
                case BrainfuckToken.TYPE_POINTER_ADVANCE:
                    buffer.append("advance();\n");
                    break;
                    
                case BrainfuckToken.TYPE_POINTER_RETREAT:
                    buffer.append("retreat();\n");
                    break;
                    
                case BrainfuckToken.TYPE_HEAP_INCREMENT:
                    buffer.append("add();\n");
                    break;
                    
                case BrainfuckToken.TYPE_HEAP_DECREMENT:
                    buffer.append("sub();\n");
                    break;
                    
                case BrainfuckToken.TYPE_STDOUT_WRITE:
                    buffer.append("stdout();\n");
                    break;
                    
                case BrainfuckToken.TYPE_LOOP_OPEN:
                    buffer.append("\n");
                    indent(buffer, i);
                    buffer.append("while(true) {\n\n");
                    ++i;
                    indent(buffer, i);
                    buffer.append("if(cells[pointer] == 0) { break; }\n");
                    break;
                    
                case BrainfuckToken.TYPE_LOOP_CLOSE:
                    buffer.append("\n");
                    --i;
                    indent(buffer, i);
                    buffer.append("}\n");
                    break;
            }
        }
        
        tail(filename, buffer);
        
        writer.write(buffer.toString().getBytes());
    }    

    private void indent(StringBuffer buffer, int depth) {
        for(int i=0; i < depth; i++) {
            buffer.append('\t');
        }
    }
    
    private void header(String filename, StringBuffer buffer) {
        buffer.append("import java.io.BufferedReader;\n");
        buffer.append("import java.io.InputStreamReader;\n\n");
        buffer.append("class ").append(filename).append(" {\n\n");
        buffer.append("\tprivate int pointer = 0;\n");
        buffer.append("\tprivate int[] cells = new int[30000];\n\n");
        buffer.append("\tprivate BufferedReader in;\n");
        buffer.append("\tpublic static void main(String[] args) throws Exception {\n\n");
        buffer.append("\t\t").append(filename).append(" instance = new ").append(filename).append("();\n");
        buffer.append("\t\tinstance.run();\n");
        buffer.append("\t}\n\n");
        buffer.append("\tpublic ").append(filename).append("() {\n");
        buffer.append("\t\tin = new BufferedReader(new InputStreamReader(System.in));\n");
        buffer.append("\t}\n\n");
        buffer.append("\tpublic void run() throws Exception {\n");
        buffer.append("\t\tfor(int i=0; i < cells.length; i++) {\n");
        buffer.append("\t\t\tcells[i] = 0;\n");
        buffer.append("\t\t}\n\n");
    }
    
    private void tail(String filename, StringBuffer buffer) {
        
        buffer.append("\n\t}\n\n");
        
        buffer.append("\tprivate void stdout() {\n");
        buffer.append("\t\tSystem.out.write((char)cells[pointer]);\n");
        buffer.append("\t\tSystem.out.flush();\n");
        buffer.append("\t}\n\n");
        
        buffer.append("\tprivate void stdin() throws Exception {\n");
        buffer.append("\t\tcells[pointer] = (int)in.readLine().charAt(0);\n");
        buffer.append("\t}\n\n");
        
        buffer.append("\tprivate void advance() {\n");
        buffer.append("\t\t++pointer;\n");
        buffer.append("\t\tif(pointer >= cells.length) {\n");
        buffer.append("\t\t\tpointer = 0;\n");
        buffer.append("\t\t}\n");
        buffer.append("\t}\n\n");
        
        buffer.append("\tprivate void retreat() {\n");
        buffer.append("\t\t--pointer;\n");
        buffer.append("\t\tif(pointer < 0) {\n");
        buffer.append("\t\t\tpointer = cells.length - 1;\n");
        buffer.append("\t\t}\n");
        buffer.append("\t}\n\n");
        
        buffer.append("\tprivate void add() {\n");
        buffer.append("\t\tcells[pointer] += 1;\n");
        buffer.append("\t\tif(cells[pointer] > 255) {\n");
        buffer.append("\t\t\tcells[pointer] = 0;\n");
        buffer.append("\t\t}\n");
        buffer.append("\t}\n\n");        
  
        buffer.append("\tprivate void sub() {\n");
        buffer.append("\t\tcells[pointer] -= 1;\n");
        buffer.append("\t\tif(cells[pointer] < 0) {\n");
        buffer.append("\t\t\tcells[pointer] = 255;\n");
        buffer.append("\t\t}\n");
        buffer.append("\t}\n\n");        
        
        buffer.append("}");
    }
    
}