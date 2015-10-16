package com.ciheul.soc.simulator.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.ciheul.soc.simulator.config.SimulatorConfig;
import com.ciheul.soc.simulator.proto.SOCServer;

public class App {
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(
				SimulatorConfig.class);

		SOCServer server = (SOCServer) context.getBean(SOCServer.class);
		server.runSimulator();
	}
}
