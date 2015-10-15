package com.ciheul.soc.simulator.proto;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import org.apache.log4j.Logger;

import com.ciheul.soc.simulator.proto.MessageProtocol.Ack;
import com.ciheul.soc.simulator.proto.MessageProtocol.SocMessage;

public class SOCServerHandler extends SimpleChannelInboundHandler<SocMessage> {

	private static final Logger logger = Logger
			.getLogger(SOCServerHandler.class);

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) {
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, SocMessage msg)
			throws Exception {
		if (msg.getFlightPlanProto() != null) {
			logger.debug("Received FPL : "
					+ msg.getFlightPlanProto().toString());
		}

		if (msg.getFlightPlanProtoList() != null) {
			logger.debug("Received FPL List : "
					+ msg.getFlightPlanProtoList().toString());
		}

		Ack.Builder ackBuilder = Ack.newBuilder().setStatus("OK");
		SocMessage.Builder socBuilder = SocMessage.newBuilder(msg).setAck(
				ackBuilder.build());
		ctx.writeAndFlush(socBuilder.build());
	}

}