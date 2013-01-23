/*******************************************************************************
 * Copyright (c) 2013 Carlos Badenes Olmedo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributor(s):
 *     cbitar
 *     cbadenes
 ******************************************************************************/
package asciidoc.maven.plugin;

import java.io.File;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import asciidoc.maven.plugin.cmd.CmdI;
import asciidoc.maven.plugin.cmd.Factory;
import asciidoc.maven.plugin.python.Executor;
import asciidoc.maven.plugin.tools.FileHelper;

/**
 * AsciiDoc Mojo.
 * 
 * @author cbitar
 * @author cbadenes
 */
@Mojo (name = "asciidoc", defaultPhase = LifecyclePhase.GENERATE_RESOURCES)
public class AsciiDocMojo extends AbstractMojo {

    @Parameter ( property="conversor", required=true)
    private String conversor;
    
    @Parameter ( property="srcfile", required=true)
    private File srcfile;

    @Parameter (property="outfile", defaultValue="${project.build.directory}", required=false)
    private File outfile;

    @Parameter (property="format", defaultValue="html5", required=false)
    private String format;

    @Parameter (property="stylesheet", required=false)
    private String stylesheet;
    
    @Parameter (property="icons", defaultValue="false", required=false)
    private boolean icons;
    
    private String iconsDir;
    
    @Parameter (property="noHeaderFooter", defaultValue="false", required=false)
    private boolean noHeaderFooter;

    @Parameter (property="lang", defaultValue="en", required=false)
    private String lang;

    private Factory cmdFactory;
    private Executor executor;
    
    public AsciiDocMojo() {
       this.cmdFactory = new Factory();
       this.executor = new Executor();
    }

    public File getSrcfile() {
        return srcfile;
    }

    public void setSrcfile(String srcfile) {
        this.srcfile = new File("."+File.separator+"src"+File.separator+"main"+File.separator+"asciidoc"+File.separator+srcfile);
    }
    
    public String getConversor() {
        return this.conversor;
    }
    
    public void setConversor(String _conversor) {
        this.conversor = _conversor;
    }

    public boolean isIcons() {
        return this.icons;
    }

    public void setIcons(boolean _icons) {
        this.icons = _icons;
        //this.iconsDir = new String(".."+File.separator+"resources"+File.separator+"images"+File.separator+"icons");
        this.iconsDir = new String("images"+File.separator+"icons");
    }
    
    public String getStylesheet() {
        return this.stylesheet;
    }

    public void setStylesheet(String _stylesheet) {
        //this.stylesheet = new String(".."+ File.separator+"resources"+File.separator+_stylesheet);
        this.stylesheet = _stylesheet;
    }
    
    public File getOutfile() {
        return outfile;
    }

    public void setOutfile(File outfile) {
        this.outfile = outfile;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String _format) {
        this.format = _format;
    }

    public boolean isNoHeaderFooter() {
        return noHeaderFooter;
    }

    public void setNoHeaderFooter(boolean noHeaderFooter) {
        this.noHeaderFooter = noHeaderFooter;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public void execute() throws MojoExecutionException, MojoFailureException {
        
        File outdir = this.outfile;
        FileHelper.copyDir(new File("./src/main/resources"), outdir);
        
        CmdI cmd = this.cmdFactory.getCommand(this.conversor).
            withSrc(this.srcfile).
            withOutput(outfile).
            withFormat(format).
            withStylesheet(stylesheet).
            withIcons(icons).
            withIconsDir(iconsDir);
        
        this.executor.exec(cmd.getOptions(), cmd.getProgram());  
        
        FileHelper.copyDir(new File("./src/main/resources"), cmd.getOutputdir());
    }

    

}

