package com.ciheul.soc.simulator.proto;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

import com.ciheul.soc.simulator.proto.MessageProtocol.SocMessage;

public class SOCServerInitializer extends ChannelInitializer<SocketChannel> {

	@Override
	public void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline p = ch.pipeline();

		p.addLast(new ProtobufVarint32FrameDecoder());
		p.addLast(new ProtobufDecoder(SocMessage.getDefaultInstance()));

		p.addLast(new ProtobufVarint32LengthFieldPrepender());
		p.addLast(new ProtobufEncoder());

		p.addLast(new SOCServerHandler());
	}
}