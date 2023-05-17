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

  private static volatile io.grpc.MethodDescriptor<com.xml2023.mainapp.getRezervacijaByIdRequest,
      com.xml2023.mainapp.getRezervacijaByIdResponse> getGetRezervacijaByIdMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getRezervacijaById",
      requestType = com.xml2023.mainapp.getRezervacijaByIdRequest.class,
      responseType = com.xml2023.mainapp.getRezervacijaByIdResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.xml2023.mainapp.getRezervacijaByIdRequest,
      com.xml2023.mainapp.getRezervacijaByIdResponse> getGetRezervacijaByIdMethod() {
    io.grpc.MethodDescriptor<com.xml2023.mainapp.getRezervacijaByIdRequest, com.xml2023.mainapp.getRezervacijaByIdResponse> getGetRezervacijaByIdMethod;
    if ((getGetRezervacijaByIdMethod = RezervacijaGrpcGrpc.getGetRezervacijaByIdMethod) == null) {
      synchronized (RezervacijaGrpcGrpc.class) {
        if ((getGetRezervacijaByIdMethod = RezervacijaGrpcGrpc.getGetRezervacijaByIdMethod) == null) {
          RezervacijaGrpcGrpc.getGetRezervacijaByIdMethod = getGetRezervacijaByIdMethod = 
              io.grpc.MethodDescriptor.<com.xml2023.mainapp.getRezervacijaByIdRequest, com.xml2023.mainapp.getRezervacijaByIdResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.xml2023.mainapp.RezervacijaGrpc", "getRezervacijaById"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xml2023.mainapp.getRezervacijaByIdRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xml2023.mainapp.getRezervacijaByIdResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new RezervacijaGrpcMethodDescriptorSupplier("getRezervacijaById"))
                  .build();
          }
        }
     }
     return getGetRezervacijaByIdMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.xml2023.mainapp.getListaRezervacijaByUserIdRequest,
      com.xml2023.mainapp.getListaRezervacijaByUserIdResponse> getGetListaRezervacijaByUserIdMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getListaRezervacijaByUserId",
      requestType = com.xml2023.mainapp.getListaRezervacijaByUserIdRequest.class,
      responseType = com.xml2023.mainapp.getListaRezervacijaByUserIdResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.xml2023.mainapp.getListaRezervacijaByUserIdRequest,
      com.xml2023.mainapp.getListaRezervacijaByUserIdResponse> getGetListaRezervacijaByUserIdMethod() {
    io.grpc.MethodDescriptor<com.xml2023.mainapp.getListaRezervacijaByUserIdRequest, com.xml2023.mainapp.getListaRezervacijaByUserIdResponse> getGetListaRezervacijaByUserIdMethod;
    if ((getGetListaRezervacijaByUserIdMethod = RezervacijaGrpcGrpc.getGetListaRezervacijaByUserIdMethod) == null) {
      synchronized (RezervacijaGrpcGrpc.class) {
        if ((getGetListaRezervacijaByUserIdMethod = RezervacijaGrpcGrpc.getGetListaRezervacijaByUserIdMethod) == null) {
          RezervacijaGrpcGrpc.getGetListaRezervacijaByUserIdMethod = getGetListaRezervacijaByUserIdMethod = 
              io.grpc.MethodDescriptor.<com.xml2023.mainapp.getListaRezervacijaByUserIdRequest, com.xml2023.mainapp.getListaRezervacijaByUserIdResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.xml2023.mainapp.RezervacijaGrpc", "getListaRezervacijaByUserId"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xml2023.mainapp.getListaRezervacijaByUserIdRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xml2023.mainapp.getListaRezervacijaByUserIdResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new RezervacijaGrpcMethodDescriptorSupplier("getListaRezervacijaByUserId"))
                  .build();
          }
        }
     }
     return getGetListaRezervacijaByUserIdMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.xml2023.mainapp.ActiveResExistsForSmestajRequest,
      com.xml2023.mainapp.ActiveResExistsForSmestajResponse> getResExistsForSmestajMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "resExistsForSmestaj",
      requestType = com.xml2023.mainapp.ActiveResExistsForSmestajRequest.class,
      responseType = com.xml2023.mainapp.ActiveResExistsForSmestajResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.xml2023.mainapp.ActiveResExistsForSmestajRequest,
      com.xml2023.mainapp.ActiveResExistsForSmestajResponse> getResExistsForSmestajMethod() {
    io.grpc.MethodDescriptor<com.xml2023.mainapp.ActiveResExistsForSmestajRequest, com.xml2023.mainapp.ActiveResExistsForSmestajResponse> getResExistsForSmestajMethod;
    if ((getResExistsForSmestajMethod = RezervacijaGrpcGrpc.getResExistsForSmestajMethod) == null) {
      synchronized (RezervacijaGrpcGrpc.class) {
        if ((getResExistsForSmestajMethod = RezervacijaGrpcGrpc.getResExistsForSmestajMethod) == null) {
          RezervacijaGrpcGrpc.getResExistsForSmestajMethod = getResExistsForSmestajMethod = 
              io.grpc.MethodDescriptor.<com.xml2023.mainapp.ActiveResExistsForSmestajRequest, com.xml2023.mainapp.ActiveResExistsForSmestajResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.xml2023.mainapp.RezervacijaGrpc", "resExistsForSmestaj"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xml2023.mainapp.ActiveResExistsForSmestajRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xml2023.mainapp.ActiveResExistsForSmestajResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new RezervacijaGrpcMethodDescriptorSupplier("resExistsForSmestaj"))
                  .build();
          }
        }
     }
     return getResExistsForSmestajMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.xml2023.mainapp.ActiveReservationsRequest,
      com.xml2023.mainapp.ActiveReservationsResponse> getGetActiveReservationsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getActiveReservations",
      requestType = com.xml2023.mainapp.ActiveReservationsRequest.class,
      responseType = com.xml2023.mainapp.ActiveReservationsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.xml2023.mainapp.ActiveReservationsRequest,
      com.xml2023.mainapp.ActiveReservationsResponse> getGetActiveReservationsMethod() {
    io.grpc.MethodDescriptor<com.xml2023.mainapp.ActiveReservationsRequest, com.xml2023.mainapp.ActiveReservationsResponse> getGetActiveReservationsMethod;
    if ((getGetActiveReservationsMethod = RezervacijaGrpcGrpc.getGetActiveReservationsMethod) == null) {
      synchronized (RezervacijaGrpcGrpc.class) {
        if ((getGetActiveReservationsMethod = RezervacijaGrpcGrpc.getGetActiveReservationsMethod) == null) {
          RezervacijaGrpcGrpc.getGetActiveReservationsMethod = getGetActiveReservationsMethod = 
              io.grpc.MethodDescriptor.<com.xml2023.mainapp.ActiveReservationsRequest, com.xml2023.mainapp.ActiveReservationsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.xml2023.mainapp.RezervacijaGrpc", "getActiveReservations"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xml2023.mainapp.ActiveReservationsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xml2023.mainapp.ActiveReservationsResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new RezervacijaGrpcMethodDescriptorSupplier("getActiveReservations"))
                  .build();
          }
        }
     }
     return getGetActiveReservationsMethod;
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

    /**
     */
    public void getRezervacijaById(com.xml2023.mainapp.getRezervacijaByIdRequest request,
        io.grpc.stub.StreamObserver<com.xml2023.mainapp.getRezervacijaByIdResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetRezervacijaByIdMethod(), responseObserver);
    }

    /**
     */
    public void getListaRezervacijaByUserId(com.xml2023.mainapp.getListaRezervacijaByUserIdRequest request,
        io.grpc.stub.StreamObserver<com.xml2023.mainapp.getListaRezervacijaByUserIdResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetListaRezervacijaByUserIdMethod(), responseObserver);
    }

    /**
     */
    public void resExistsForSmestaj(com.xml2023.mainapp.ActiveResExistsForSmestajRequest request,
        io.grpc.stub.StreamObserver<com.xml2023.mainapp.ActiveResExistsForSmestajResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getResExistsForSmestajMethod(), responseObserver);
    }

    /**
     */
    public void getActiveReservations(com.xml2023.mainapp.ActiveReservationsRequest request,
        io.grpc.stub.StreamObserver<com.xml2023.mainapp.ActiveReservationsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetActiveReservationsMethod(), responseObserver);
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
          .addMethod(
            getGetRezervacijaByIdMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.xml2023.mainapp.getRezervacijaByIdRequest,
                com.xml2023.mainapp.getRezervacijaByIdResponse>(
                  this, METHODID_GET_REZERVACIJA_BY_ID)))
          .addMethod(
            getGetListaRezervacijaByUserIdMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.xml2023.mainapp.getListaRezervacijaByUserIdRequest,
                com.xml2023.mainapp.getListaRezervacijaByUserIdResponse>(
                  this, METHODID_GET_LISTA_REZERVACIJA_BY_USER_ID)))
          .addMethod(
            getResExistsForSmestajMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.xml2023.mainapp.ActiveResExistsForSmestajRequest,
                com.xml2023.mainapp.ActiveResExistsForSmestajResponse>(
                  this, METHODID_RES_EXISTS_FOR_SMESTAJ)))
          .addMethod(
            getGetActiveReservationsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.xml2023.mainapp.ActiveReservationsRequest,
                com.xml2023.mainapp.ActiveReservationsResponse>(
                  this, METHODID_GET_ACTIVE_RESERVATIONS)))
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

    /**
     */
    public void getRezervacijaById(com.xml2023.mainapp.getRezervacijaByIdRequest request,
        io.grpc.stub.StreamObserver<com.xml2023.mainapp.getRezervacijaByIdResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetRezervacijaByIdMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getListaRezervacijaByUserId(com.xml2023.mainapp.getListaRezervacijaByUserIdRequest request,
        io.grpc.stub.StreamObserver<com.xml2023.mainapp.getListaRezervacijaByUserIdResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetListaRezervacijaByUserIdMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void resExistsForSmestaj(com.xml2023.mainapp.ActiveResExistsForSmestajRequest request,
        io.grpc.stub.StreamObserver<com.xml2023.mainapp.ActiveResExistsForSmestajResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getResExistsForSmestajMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getActiveReservations(com.xml2023.mainapp.ActiveReservationsRequest request,
        io.grpc.stub.StreamObserver<com.xml2023.mainapp.ActiveReservationsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetActiveReservationsMethod(), getCallOptions()), request, responseObserver);
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

    /**
     */
    public com.xml2023.mainapp.getRezervacijaByIdResponse getRezervacijaById(com.xml2023.mainapp.getRezervacijaByIdRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetRezervacijaByIdMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.xml2023.mainapp.getListaRezervacijaByUserIdResponse getListaRezervacijaByUserId(com.xml2023.mainapp.getListaRezervacijaByUserIdRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetListaRezervacijaByUserIdMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.xml2023.mainapp.ActiveResExistsForSmestajResponse resExistsForSmestaj(com.xml2023.mainapp.ActiveResExistsForSmestajRequest request) {
      return blockingUnaryCall(
          getChannel(), getResExistsForSmestajMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.xml2023.mainapp.ActiveReservationsResponse getActiveReservations(com.xml2023.mainapp.ActiveReservationsRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetActiveReservationsMethod(), getCallOptions(), request);
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

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.xml2023.mainapp.getRezervacijaByIdResponse> getRezervacijaById(
        com.xml2023.mainapp.getRezervacijaByIdRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetRezervacijaByIdMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.xml2023.mainapp.getListaRezervacijaByUserIdResponse> getListaRezervacijaByUserId(
        com.xml2023.mainapp.getListaRezervacijaByUserIdRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetListaRezervacijaByUserIdMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.xml2023.mainapp.ActiveResExistsForSmestajResponse> resExistsForSmestaj(
        com.xml2023.mainapp.ActiveResExistsForSmestajRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getResExistsForSmestajMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.xml2023.mainapp.ActiveReservationsResponse> getActiveReservations(
        com.xml2023.mainapp.ActiveReservationsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetActiveReservationsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_EXISTS = 0;
  private static final int METHODID_RESERVATIONS_FOR_USER_EXISTS = 1;
  private static final int METHODID_GET_REZERVACIJA_BY_ID = 2;
  private static final int METHODID_GET_LISTA_REZERVACIJA_BY_USER_ID = 3;
  private static final int METHODID_RES_EXISTS_FOR_SMESTAJ = 4;
  private static final int METHODID_GET_ACTIVE_RESERVATIONS = 5;

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
        case METHODID_GET_REZERVACIJA_BY_ID:
          serviceImpl.getRezervacijaById((com.xml2023.mainapp.getRezervacijaByIdRequest) request,
              (io.grpc.stub.StreamObserver<com.xml2023.mainapp.getRezervacijaByIdResponse>) responseObserver);
          break;
        case METHODID_GET_LISTA_REZERVACIJA_BY_USER_ID:
          serviceImpl.getListaRezervacijaByUserId((com.xml2023.mainapp.getListaRezervacijaByUserIdRequest) request,
              (io.grpc.stub.StreamObserver<com.xml2023.mainapp.getListaRezervacijaByUserIdResponse>) responseObserver);
          break;
        case METHODID_RES_EXISTS_FOR_SMESTAJ:
          serviceImpl.resExistsForSmestaj((com.xml2023.mainapp.ActiveResExistsForSmestajRequest) request,
              (io.grpc.stub.StreamObserver<com.xml2023.mainapp.ActiveResExistsForSmestajResponse>) responseObserver);
          break;
        case METHODID_GET_ACTIVE_RESERVATIONS:
          serviceImpl.getActiveReservations((com.xml2023.mainapp.ActiveReservationsRequest) request,
              (io.grpc.stub.StreamObserver<com.xml2023.mainapp.ActiveReservationsResponse>) responseObserver);
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
              .addMethod(getGetRezervacijaByIdMethod())
              .addMethod(getGetListaRezervacijaByUserIdMethod())
              .addMethod(getResExistsForSmestajMethod())
              .addMethod(getGetActiveReservationsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
