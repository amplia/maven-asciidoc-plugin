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

import org.apache.commons.lang.StringUtils;

import asciidoc.maven.plugin.tools.FileHelper;

public class A2x extends Abstract{

    public A2x(File _asciiHome) {
        super("a2x", _asciiHome);        
    }

    @Override
    public LinkedList<String> getOptions() {
        LinkedList<String> options = new LinkedList<String>();
        if (stylesheet != null) 
            options.add("--stylesheet=" + this.stylesheet);        
        if (this.icons){
            options.add("--icons");
            options.add("--icons-dir="+this.iconsDir);
        }
        if (this.verbose){
            options.add("--verbose");
        }
        options.add("-f" + this.format);
        options.add("-D" + getOutput().getAbsolutePath());
        options.add(this.srcFile.getAbsolutePath());
        return options;
    }

    @Override
    public File getOutputdir() {
        String name = StringUtils.substringBefore(this.srcFile.getName(), "."+FileHelper.getFileExtension(srcFile));
        return new File(getOutput(),name+"."+this.format);
    }    
}

