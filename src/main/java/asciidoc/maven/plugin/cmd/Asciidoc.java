/*******************************************************************************
 * Copyright (c) 2013 Carlos Badenes Olmedo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributor(s):
 *     cbadenes
 ******************************************************************************/
package asciidoc.maven.plugin.cmd;

import java.io.File;
import java.util.LinkedList;

public class Asciidoc extends Abstract{

    public Asciidoc(File _asciiHome) {
        super("asciidoc", _asciiHome);
    }
    
    @Override
    public LinkedList<String> getOptions() {
        File outputFile = getOutput();
        if (outputFile.isDirectory()){
            outputFile = new File(getOutput(),this.srcFile.getName()+"."+this.format);            
        }
        
        LinkedList<String> options = new LinkedList<String>();
        options.add(getProgram().getAbsolutePath());
        options.add(("-b" + this.format));
        options.add("-atoc2");
        options.add("-apygments");
        options.add("-o" + outputFile.getAbsolutePath());
        // addArgv("-aicons"));
        // addArgv("-adata-uri"));
        // addArgv("-atheme=default"));
        if (this.noHeaderFooter){
            options.add("--no-header-footer");
        }
        if (this.verbose){
            options.add("--verbose");
        }
        options.add(this.srcFile.getAbsolutePath());
        return options;
    }

    @Override
    public File getOutputdir() {
        return getOutput();
    }
    
}

