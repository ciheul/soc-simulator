package com.ciheul.soc.simulator.main;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

import com.ciheul.soc.simulator.proto.SOCServerInitializer;

public class SocSimulator {

	@Value("${soc.simulator.port}")
	static int port = 8463;

	private static final Logger logger = Logger.getLogger(SocSimulator.class);

	public static void main(String[] args) throws Exception {

		EventLoopGroup bossGroup = new NioEventLoopGroup(1);
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup)
					.channel(NioServerSocketChannel.class)
					.handler(new LoggingHandler(LogLevel.DEBUG))
					.childHandler(new SOCServerInitializer());

			logger.debug("SOC Server Simulator run at port " + port + "..");
			b.bind(port).sync().channel().closeFuture().sync();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
}
