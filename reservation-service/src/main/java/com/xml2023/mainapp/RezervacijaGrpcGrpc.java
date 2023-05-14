package com.xml2023.mainapp;

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
    comments = "Source: temp.proto")
public final class RezervacijaGrpcGrpc {

  private RezervacijaGrpcGrpc() {}

  public static final String SERVICE_NAME = "com.xml2023.mainapp.RezervacijaGrpc";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.xml2023.mainapp.RezervacijaExistsRequest,
      com.xml2023.mainapp.RezervacijaExistsResponse> getExistsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "exists",
      requestType = com.xml2023.mainapp.RezervacijaExistsRequest.class,
      responseType = com.xml2023.mainapp.RezervacijaExistsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.xml2023.mainapp.RezervacijaExistsRequest,
      com.xml2023.mainapp.RezervacijaExistsResponse> getExistsMethod() {
    io.grpc.MethodDescriptor<com.xml2023.mainapp.RezervacijaExistsRequest, com.xml2023.mainapp.RezervacijaExistsResponse> getExistsMethod;
    if ((getExistsMethod = RezervacijaGrpcGrpc.getExistsMethod) == null) {
      synchronized (RezervacijaGrpcGrpc.class) {
        if ((getExistsMethod = RezervacijaGrpcGrpc.getExistsMethod) == null) {
          RezervacijaGrpcGrpc.getExistsMethod = getExistsMethod = 
              io.grpc.MethodDescriptor.<com.xml2023.mainapp.RezervacijaExistsRequest, com.xml2023.mainapp.RezervacijaExistsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.xml2023.mainapp.RezervacijaGrpc", "exists"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xml2023.mainapp.RezervacijaExistsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xml2023.mainapp.RezervacijaExistsResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new RezervacijaGrpcMethodDescriptorSupplier("exists"))
                  .build();
          }
        }
     }
     return getExistsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.xml2023.mainapp.ActiveResExistsRequest,
      com.xml2023.mainapp.ActiveResExistsResponse> getReservationsForUserExistsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "reservationsForUserExists",
      requestType = com.xml2023.mainapp.ActiveResExistsRequest.class,
      responseType = com.xml2023.mainapp.ActiveResExistsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.xml2023.mainapp.ActiveResExistsRequest,
      com.xml2023.mainapp.ActiveResExistsResponse> getReservationsForUserExistsMethod() {
    io.grpc.MethodDescriptor<com.xml2023.mainapp.ActiveResExistsRequest, com.xml2023.mainapp.ActiveResExistsResponse> getReservationsForUserExistsMethod;
    if ((getReservationsForUserExistsMethod = RezervacijaGrpcGrpc.getReservationsForUserExistsMethod) == null) {
      synchronized (RezervacijaGrpcGrpc.class) {
        if ((getReservationsForUserExistsMethod = RezervacijaGrpcGrpc.getReservationsForUserExistsMethod) == null) {
          RezervacijaGrpcGrpc.getReservationsForUserExistsMethod = getReservationsForUserExistsMethod = 
              io.grpc.MethodDescriptor.<com.xml2023.mainapp.ActiveResExistsRequest, com.xml2023.mainapp.ActiveResExistsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.xml2023.mainapp.RezervacijaGrpc", "reservationsForUserExists"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xml2023.mainapp.ActiveResExistsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xml2023.mainapp.ActiveResExistsResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new RezervacijaGrpcMethodDescriptorSupplier("reservationsForUserExists"))
                  .build();
          }
        }
     }
     return getReservationsForUserExistsMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static RezervacijaGrpcStub newStub(io.grpc.Channel channel) {
    return new RezervacijaGrpcStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static RezervacijaGrpcBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new RezervacijaGrpcBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static RezervacijaGrpcFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new RezervacijaGrpcFutureStub(channel);
  }

  /**
   */
  public static abstract class RezervacijaGrpcImplBase implements io.grpc.BindableService {

    /**
     */
    public void exists(com.xml2023.mainapp.RezervacijaExistsRequest request,
        io.grpc.stub.StreamObserver<com.xml2023.mainapp.RezervacijaExistsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getExistsMethod(), responseObserver);
    }

    /**
     */
    public void reservationsForUserExists(com.xml2023.mainapp.ActiveResExistsRequest request,
        io.grpc.stub.StreamObserver<com.xml2023.mainapp.ActiveResExistsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getReservationsForUserExistsMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getExistsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.xml2023.mainapp.RezervacijaExistsRequest,
                com.xml2023.mainapp.RezervacijaExistsResponse>(
                  this, METHODID_EXISTS)))
          .addMethod(
            getReservationsForUserExistsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.xml2023.mainapp.ActiveResExistsRequest,
                com.xml2023.mainapp.ActiveResExistsResponse>(
                  this, METHODID_RESERVATIONS_FOR_USER_EXISTS)))
          .build();
    }
  }

  /**
   */
  public static final class RezervacijaGrpcStub extends io.grpc.stub.AbstractStub<RezervacijaGrpcStub> {
    private RezervacijaGrpcStub(io.grpc.Channel channel) {
      super(channel);
    }

    private RezervacijaGrpcStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RezervacijaGrpcStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new RezervacijaGrpcStub(channel, callOptions);
    }

    /**
     */
    public void exists(com.xml2023.mainapp.RezervacijaExistsRequest request,
        io.grpc.stub.StreamObserver<com.xml2023.mainapp.RezervacijaExistsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getExistsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void reservationsForUserExists(com.xml2023.mainapp.ActiveResExistsRequest request,
        io.grpc.stub.StreamObserver<com.xml2023.mainapp.ActiveResExistsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getReservationsForUserExistsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class RezervacijaGrpcBlockingStub extends io.grpc.stub.AbstractStub<RezervacijaGrpcBlockingStub> {
    private RezervacijaGrpcBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private RezervacijaGrpcBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RezervacijaGrpcBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new RezervacijaGrpcBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.xml2023.mainapp.RezervacijaExistsResponse exists(com.xml2023.mainapp.RezervacijaExistsRequest request) {
      return blockingUnaryCall(
          getChannel(), getExistsMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.xml2023.mainapp.ActiveResExistsResponse reservationsForUserExists(com.xml2023.mainapp.ActiveResExistsRequest request) {
      return blockingUnaryCall(
          getChannel(), getReservationsForUserExistsMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class RezervacijaGrpcFutureStub extends io.grpc.stub.AbstractStub<RezervacijaGrpcFutureStub> {
    private RezervacijaGrpcFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private RezervacijaGrpcFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RezervacijaGrpcFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new RezervacijaGrpcFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.xml2023.mainapp.RezervacijaExistsResponse> exists(
        com.xml2023.mainapp.RezervacijaExistsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getExistsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.xml2023.mainapp.ActiveResExistsResponse> reservationsForUserExists(
        com.xml2023.mainapp.ActiveResExistsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getReservationsForUserExistsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_EXISTS = 0;
  private static final int METHODID_RESERVATIONS_FOR_USER_EXISTS = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final RezervacijaGrpcImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(RezervacijaGrpcImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_EXISTS:
          serviceImpl.exists((com.xml2023.mainapp.RezervacijaExistsRequest) request,
              (io.grpc.stub.StreamObserver<com.xml2023.mainapp.RezervacijaExistsResponse>) responseObserver);
          break;
        case METHODID_RESERVATIONS_FOR_USER_EXISTS:
          serviceImpl.reservationsForUserExists((com.xml2023.mainapp.ActiveResExistsRequest) request,
              (io.grpc.stub.StreamObserver<com.xml2023.mainapp.ActiveResExistsResponse>) responseObserver);
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

  private static abstract class RezervacijaGrpcBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    RezervacijaGrpcBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.xml2023.mainapp.Temp.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("RezervacijaGrpc");
    }
  }

  private static final class RezervacijaGrpcFileDescriptorSupplier
      extends RezervacijaGrpcBaseDescriptorSupplier {
    RezervacijaGrpcFileDescriptorSupplier() {}
  }

  private static final class RezervacijaGrpcMethodDescriptorSupplier
      extends RezervacijaGrpcBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    RezervacijaGrpcMethodDescriptorSupplier(String methodName) {
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
      synchronized (RezervacijaGrpcGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new RezervacijaGrpcFileDescriptorSupplier())
              .addMethod(getExistsMethod())
              .addMethod(getReservationsForUserExistsMethod())
              .build();
        }
      }
    }
    return result;
  }
}