package sample.plugin;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.IOUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

@Mojo(name = "wrapjar")
public class WrapJarArchive extends AbstractMojo {
	@Parameter(defaultValue = "${project}", required = true, readonly = true)
	private MavenProject project;

	public void execute() throws MojoExecutionException {
		String outputDirectory = project.getBuild().getDirectory();
		String finalName = project.getBuild().getFinalName();
		String packaging = project.getPackaging();
		File buildedArchive = new File(outputDirectory + File.separator + finalName + "." + packaging);
		try (ZipOutputStream zipOutputStream = new ZipOutputStream(
				new BufferedOutputStream(new FileOutputStream(outputDirectory + File.separator + finalName  + ".zip")));
				BufferedInputStream buildedArchiveInputStream = new BufferedInputStream(
						new FileInputStream(buildedArchive))) {
			ZipEntry buildedArchiveEntry = new ZipEntry(buildedArchive.getName());
			zipOutputStream.putNextEntry(buildedArchiveEntry);
			IOUtils.copy(buildedArchiveInputStream, zipOutputStream);
		} catch (IOException e) {
			throw new MojoExecutionException("Error occured while wrapping archive");
		}
	}
}