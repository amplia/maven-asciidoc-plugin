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

import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugin.logging.SystemStreamLog;

public abstract class Abstract implements CmdI{

    Log log;
    
    private String id;
    private String program;
    protected String format;
    
    protected File asciidocHome;
    protected File srcFile;
    protected File outFile;
    protected String stylesheet;
    protected String iconsDir;
    protected String encoding;
    protected boolean book;
    protected boolean icons;
    protected boolean verbose;
    protected boolean noHeaderFooter;
    protected String lang;
    
    protected String traductor;
    
    public Abstract(String _id, File _asciidocHome) {
        this.log = new SystemStreamLog();
        this.id = _id;
        this.program = _id + ".py";
        this.asciidocHome = _asciidocHome;        
    }
    
    @Override
    public CmdI withTraductor(String _value){
    	this.traductor = _value;
    	return this;
    }
    
    
    @Override
    public CmdI withFormat(String _value) {
        this.format = _value;
        return this;
    }
    
    @Override
    public CmdI withEncoding(String _value){
    	this.encoding = _value;
    	return this;
    }
    
    public CmdI withBook(boolean _value){
    	this.book = _value;
    	return this;
    }

    @Override
    public CmdI withSrc(File _file){
        this.srcFile = _file;
        return this;
    }
    
    @Override
    public CmdI withOutput(File _file) {
        this.outFile = _file;
        return this;
    }
    
    @Override
    public CmdI withStylesheet(String _file) {
        this.stylesheet = _file;
        return this;
    }
    
    @Override
    public CmdI withIcons(boolean _bool) {
        this.icons = _bool;
        return this;
    }
    
    @Override
    public CmdI withIconsDir(String _file) {
        this.iconsDir = _file;
        return this;
    }
    
    @Override
    public CmdI withVerbose(boolean _verbose) {
        this.verbose = _verbose;
        return this;
    }
    
    @Override
    public CmdI withNonHeaderFooter(boolean _noHeaderFooter) {
        this.noHeaderFooter = _noHeaderFooter;
        return this;
    }
    
    @Override
    public CmdI withLanguage(String _lang){
        this.lang = _lang;
        return this;
    }    
    
    public File getProgram(){
        return new File(this.asciidocHome + File.separator + this.program);
    }
    
    public Log getLog(){
        return this.log;
    }
    
    protected File getOutput(){
        this.outFile.mkdirs();
        return this.outFile;
    }
    
    
    public String getId(){
        return this.id;
    }   
}

