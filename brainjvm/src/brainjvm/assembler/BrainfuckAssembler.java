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

package brainjvm.assembler;

import brainjvm.parser.BrainfuckToken;
import brainjvm.parser.BrainfuckTokenManager;
import brainjvm.parser.BrainfuckParser;

import brainjvm.utils.IBrainfuckOutputWriter;
import brainjvm.utils.BrainfuckStringOutputWriter;
import brainjvm.utils.BrainfuckFileOutputWriter;

import brainjvm.core.IBrainfuckAssembler;
import brainjvm.compiler.BrainfuckCompiler;

import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.io.ByteArrayOutputStream;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject.Kind;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.FileObject;

/**
 * Compiles Brainfuck source code to JVM byte code
 * @class BrainfuckAssembler
 * @implements IBrainfuckAssembler
 * @author dan
 */
public class BrainfuckAssembler
        implements IBrainfuckAssembler {

    private IBrainfuckOutputWriter writer;

    public BrainfuckAssembler() {
    }

    public void setOutputWriter(IBrainfuckOutputWriter nwriter) {
        writer = nwriter;
    }

    public void compile(String filename, String code) throws Exception {

        StringBuffer buffer = new StringBuffer();

        BrainfuckCompiler compiler = new BrainfuckCompiler();
        BrainfuckStringOutputWriter writer = new BrainfuckStringOutputWriter(buffer);
        compiler.setOutputWriter(writer);
        compiler.compile(filename, code);

        JavaCompiler c = ToolProvider.getSystemJavaCompiler();
        MemoryFileManager fileManager = new MemoryFileManager(c);

        Source file = new Source(filename, Kind.SOURCE, buffer.toString());
        Iterable<? extends JavaFileObject> compilationUnits = Arrays.asList(file);
        
        c.getTask(null, fileManager, null, null, null, compilationUnits).call();

        Output mc = (Output)fileManager.map.remove(filename);
        if(mc != null) {
            this.writer.write(mc.toByteArray());
        }

    }

    // code below was derrived from examples at http://weblogs.java.net/blog/malenkov/archive/2008/12/how_to_compile.html

    class Source extends SimpleJavaFileObject {

        private final String content;

        Source(String name, Kind kind, String content) {
            super(URI.create("memo:///" + name.replace('.', '/') + kind.extension), kind);
            this.content = content;
        }

        @Override
        public CharSequence getCharContent(boolean ignore) {
            return this.content;
        }
        
    }

    class Output extends SimpleJavaFileObject {

        private final ByteArrayOutputStream baos = new ByteArrayOutputStream();

        Output(String name, Kind kind) {
            super(URI.create("memo:///" + name.replace('.', '/') + kind.extension), kind);
        }

        byte[] toByteArray() {
            return this.baos.toByteArray();
        }

        @Override
        public ByteArrayOutputStream openOutputStream() {
            return this.baos;
        }
        
    }

    class MemoryFileManager extends ForwardingJavaFileManager {

        private final Map<String, Output> map = new HashMap<String, Output>();

        MemoryFileManager(JavaCompiler compiler) {
            super(compiler.getStandardFileManager(null, null, null));
        }

        @Override
        public Output getJavaFileForOutput(Location location, String name, Kind kind, FileObject source) {
            Output mc = new Output(name, kind);
            this.map.put(name, mc);
            return mc;
        }
        
    }
    
}