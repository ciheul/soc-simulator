package com.ciheul.soc.simulator.proto;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ciheul.soc.simulator.proto.SOCServerInitializer;

@Component
public class SOCServer {

	@Value("${soc.simulator.port}")
	int port;

	private static final Logger logger = Logger.getLogger(SOCServer.class);

	
	// public void setPort(int port) {
	// SocServer.port = port;
	// }

	//public static void main(String[] args) {
	public void runSimulator(){
		EventLoopGroup bossGroup = new NioEventLoopGroup(1);
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup)
					.channel(NioServerSocketChannel.class)
					.handler(new LoggingHandler(LogLevel.DEBUG))
					.childHandler(new SOCServerInitializer());
			System.out.println("SOC Server Simulator run at port " + port
					+ "..");
			logger.debug("SOC Server Simulator run at port " + port + "..");
			b.bind(port).sync().channel().closeFuture().sync();
		} catch (Exception ex) {
			logger.error("Error on soc simulator : " + ex.getMessage());
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
}
