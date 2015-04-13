package io.github.zikani03.acescrape;

import java.util.List;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import spark.Spark;

public abstract class AbstractApplication {

	private final Logger LOG = LoggerFactory.getLogger(AbstractApplication.class);
	protected List<Controller> controllers;
	private String staticAssetsDir;
	
	protected AbstractApplication() {
		this.controllers = new ArrayList<Controller>();
	}
	
	protected void run(String... args) {
		this.configureControllers();
		this.configureStaticAssets();
		this.execControllers();
		LOG.info("All Controllers registered... Running Application");
	}
	
	public void setStaticAssetsDir(String staticAssetsDir) {
		this.staticAssetsDir = staticAssetsDir;
	}
	
	public abstract void configureControllers();
	
	public void addResource(Controller controller) {
		this.addController(controller);
	}
	
	public void addController(Controller controller) {
		this.controllers.add(controller);
	}
	
	protected void execControllers() {
		this.controllers.forEach((controller) -> controller.registerRoutes());
	}
	
	private void configureStaticAssets() {
		Spark.staticFileLocation(this.staticAssetsDir);
	}
}
