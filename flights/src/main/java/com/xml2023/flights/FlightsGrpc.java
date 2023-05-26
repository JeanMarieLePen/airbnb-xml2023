package com.xml2023.flights;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: flights.proto")
public final class FlightsGrpc {

  private FlightsGrpc() {}

  public static final String SERVICE_NAME = "com.xml2023.flights.Flights";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.xml2023.flights.FlightsOuterClass.TokenValidanRequest,
      com.xml2023.flights.FlightsOuterClass.TokenValidanResponse> getValidateTokenMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "validateToken",
      requestType = com.xml2023.flights.FlightsOuterClass.TokenValidanRequest.class,
      responseType = com.xml2023.flights.FlightsOuterClass.TokenValidanResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.xml2023.flights.FlightsOuterClass.TokenValidanRequest,
      com.xml2023.flights.FlightsOuterClass.TokenValidanResponse> getValidateTokenMethod() {
    io.grpc.MethodDescriptor<com.xml2023.flights.FlightsOuterClass.TokenValidanRequest, com.xml2023.flights.FlightsOuterClass.TokenValidanResponse> getValidateTokenMethod;
    if ((getValidateTokenMethod = FlightsGrpc.getValidateTokenMethod) == null) {
      synchronized (FlightsGrpc.class) {
        if ((getValidateTokenMethod = FlightsGrpc.getValidateTokenMethod) == null) {
          FlightsGrpc.getValidateTokenMethod = getValidateTokenMethod = 
              io.grpc.MethodDescriptor.<com.xml2023.flights.FlightsOuterClass.TokenValidanRequest, com.xml2023.flights.FlightsOuterClass.TokenValidanResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.xml2023.flights.Flights", "validateToken"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xml2023.flights.FlightsOuterClass.TokenValidanRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xml2023.flights.FlightsOuterClass.TokenValidanResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new FlightsMethodDescriptorSupplier("validateToken"))
                  .build();
          }
        }
     }
     return getValidateTokenMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static FlightsStub newStub(io.grpc.Channel channel) {
    return new FlightsStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static FlightsBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new FlightsBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static FlightsFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new FlightsFutureStub(channel);
  }

  /**
   */
  public static abstract class FlightsImplBase implements io.grpc.BindableService {

    /**
     */
    public void validateToken(com.xml2023.flights.FlightsOuterClass.TokenValidanRequest request,
        io.grpc.stub.StreamObserver<com.xml2023.flights.FlightsOuterClass.TokenValidanResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getValidateTokenMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getValidateTokenMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.xml2023.flights.FlightsOuterClass.TokenValidanRequest,
                com.xml2023.flights.FlightsOuterClass.TokenValidanResponse>(
                  this, METHODID_VALIDATE_TOKEN)))
          .build();
    }
  }

  /**
   */
  public static final class FlightsStub extends io.grpc.stub.AbstractStub<FlightsStub> {
    private FlightsStub(io.grpc.Channel channel) {
      super(channel);
    }

    private FlightsStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FlightsStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new FlightsStub(channel, callOptions);
    }

    /**
     */
    public void validateToken(com.xml2023.flights.FlightsOuterClass.TokenValidanRequest request,
        io.grpc.stub.StreamObserver<com.xml2023.flights.FlightsOuterClass.TokenValidanResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getValidateTokenMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class FlightsBlockingStub extends io.grpc.stub.AbstractStub<FlightsBlockingStub> {
    private FlightsBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private FlightsBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FlightsBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new FlightsBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.xml2023.flights.FlightsOuterClass.TokenValidanResponse validateToken(com.xml2023.flights.FlightsOuterClass.TokenValidanRequest request) {
      return blockingUnaryCall(
          getChannel(), getValidateTokenMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class FlightsFutureStub extends io.grpc.stub.AbstractStub<FlightsFutureStub> {
    private FlightsFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private FlightsFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FlightsFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new FlightsFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.xml2023.flights.FlightsOuterClass.TokenValidanResponse> validateToken(
        com.xml2023.flights.FlightsOuterClass.TokenValidanRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getValidateTokenMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_VALIDATE_TOKEN = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final FlightsImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(FlightsImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_VALIDATE_TOKEN:
          serviceImpl.validateToken((com.xml2023.flights.FlightsOuterClass.TokenValidanRequest) request,
              (io.grpc.stub.StreamObserver<com.xml2023.flights.FlightsOuterClass.TokenValidanResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class FlightsBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    FlightsBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.xml2023.flights.FlightsOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Flights");
    }
  }

  private static final class FlightsFileDescriptorSupplier
      extends FlightsBaseDescriptorSupplier {
    FlightsFileDescriptorSupplier() {}
  }

  private static final class FlightsMethodDescriptorSupplier
      extends FlightsBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    FlightsMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (FlightsGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new FlightsFileDescriptorSupplier())
              .addMethod(getValidateTokenMethod())
              .build();
        }
      }
    }
    return result;
  }
}
