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
public final class KorisnikGrpcGrpc {

  private KorisnikGrpcGrpc() {}

  public static final String SERVICE_NAME = "com.xml2023.mainapp.KorisnikGrpc";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.xml2023.mainapp.getHostRequest,
      com.xml2023.mainapp.getHostResponse> getGetHostMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getHost",
      requestType = com.xml2023.mainapp.getHostRequest.class,
      responseType = com.xml2023.mainapp.getHostResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.xml2023.mainapp.getHostRequest,
      com.xml2023.mainapp.getHostResponse> getGetHostMethod() {
    io.grpc.MethodDescriptor<com.xml2023.mainapp.getHostRequest, com.xml2023.mainapp.getHostResponse> getGetHostMethod;
    if ((getGetHostMethod = KorisnikGrpcGrpc.getGetHostMethod) == null) {
      synchronized (KorisnikGrpcGrpc.class) {
        if ((getGetHostMethod = KorisnikGrpcGrpc.getGetHostMethod) == null) {
          KorisnikGrpcGrpc.getGetHostMethod = getGetHostMethod = 
              io.grpc.MethodDescriptor.<com.xml2023.mainapp.getHostRequest, com.xml2023.mainapp.getHostResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.xml2023.mainapp.KorisnikGrpc", "getHost"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xml2023.mainapp.getHostRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xml2023.mainapp.getHostResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new KorisnikGrpcMethodDescriptorSupplier("getHost"))
                  .build();
          }
        }
     }
     return getGetHostMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.xml2023.mainapp.rezOtkazanaRequest,
      com.xml2023.mainapp.rezOtkazanaResponse> getRezOtkazanaMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "rezOtkazana",
      requestType = com.xml2023.mainapp.rezOtkazanaRequest.class,
      responseType = com.xml2023.mainapp.rezOtkazanaResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.xml2023.mainapp.rezOtkazanaRequest,
      com.xml2023.mainapp.rezOtkazanaResponse> getRezOtkazanaMethod() {
    io.grpc.MethodDescriptor<com.xml2023.mainapp.rezOtkazanaRequest, com.xml2023.mainapp.rezOtkazanaResponse> getRezOtkazanaMethod;
    if ((getRezOtkazanaMethod = KorisnikGrpcGrpc.getRezOtkazanaMethod) == null) {
      synchronized (KorisnikGrpcGrpc.class) {
        if ((getRezOtkazanaMethod = KorisnikGrpcGrpc.getRezOtkazanaMethod) == null) {
          KorisnikGrpcGrpc.getRezOtkazanaMethod = getRezOtkazanaMethod = 
              io.grpc.MethodDescriptor.<com.xml2023.mainapp.rezOtkazanaRequest, com.xml2023.mainapp.rezOtkazanaResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.xml2023.mainapp.KorisnikGrpc", "rezOtkazana"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xml2023.mainapp.rezOtkazanaRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xml2023.mainapp.rezOtkazanaResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new KorisnikGrpcMethodDescriptorSupplier("rezOtkazana"))
                  .build();
          }
        }
     }
     return getRezOtkazanaMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.xml2023.mainapp.rezOtkazanaHostRequest,
      com.xml2023.mainapp.rezOtkazanaHostResponse> getRezOtkazanaHostMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "rezOtkazanaHost",
      requestType = com.xml2023.mainapp.rezOtkazanaHostRequest.class,
      responseType = com.xml2023.mainapp.rezOtkazanaHostResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.xml2023.mainapp.rezOtkazanaHostRequest,
      com.xml2023.mainapp.rezOtkazanaHostResponse> getRezOtkazanaHostMethod() {
    io.grpc.MethodDescriptor<com.xml2023.mainapp.rezOtkazanaHostRequest, com.xml2023.mainapp.rezOtkazanaHostResponse> getRezOtkazanaHostMethod;
    if ((getRezOtkazanaHostMethod = KorisnikGrpcGrpc.getRezOtkazanaHostMethod) == null) {
      synchronized (KorisnikGrpcGrpc.class) {
        if ((getRezOtkazanaHostMethod = KorisnikGrpcGrpc.getRezOtkazanaHostMethod) == null) {
          KorisnikGrpcGrpc.getRezOtkazanaHostMethod = getRezOtkazanaHostMethod = 
              io.grpc.MethodDescriptor.<com.xml2023.mainapp.rezOtkazanaHostRequest, com.xml2023.mainapp.rezOtkazanaHostResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.xml2023.mainapp.KorisnikGrpc", "rezOtkazanaHost"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xml2023.mainapp.rezOtkazanaHostRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xml2023.mainapp.rezOtkazanaHostResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new KorisnikGrpcMethodDescriptorSupplier("rezOtkazanaHost"))
                  .build();
          }
        }
     }
     return getRezOtkazanaHostMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.xml2023.mainapp.reservationApprovedNotificationRequest,
      com.xml2023.mainapp.reservationApprovedNotificationResponse> getRezObavestenjeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "rezObavestenje",
      requestType = com.xml2023.mainapp.reservationApprovedNotificationRequest.class,
      responseType = com.xml2023.mainapp.reservationApprovedNotificationResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.xml2023.mainapp.reservationApprovedNotificationRequest,
      com.xml2023.mainapp.reservationApprovedNotificationResponse> getRezObavestenjeMethod() {
    io.grpc.MethodDescriptor<com.xml2023.mainapp.reservationApprovedNotificationRequest, com.xml2023.mainapp.reservationApprovedNotificationResponse> getRezObavestenjeMethod;
    if ((getRezObavestenjeMethod = KorisnikGrpcGrpc.getRezObavestenjeMethod) == null) {
      synchronized (KorisnikGrpcGrpc.class) {
        if ((getRezObavestenjeMethod = KorisnikGrpcGrpc.getRezObavestenjeMethod) == null) {
          KorisnikGrpcGrpc.getRezObavestenjeMethod = getRezObavestenjeMethod = 
              io.grpc.MethodDescriptor.<com.xml2023.mainapp.reservationApprovedNotificationRequest, com.xml2023.mainapp.reservationApprovedNotificationResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.xml2023.mainapp.KorisnikGrpc", "rezObavestenje"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xml2023.mainapp.reservationApprovedNotificationRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xml2023.mainapp.reservationApprovedNotificationResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new KorisnikGrpcMethodDescriptorSupplier("rezObavestenje"))
                  .build();
          }
        }
     }
     return getRezObavestenjeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.xml2023.mainapp.NekoRezervisaoRequest,
      com.xml2023.mainapp.NekoRezervisaoResponse> getNewRezNotifyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "newRezNotify",
      requestType = com.xml2023.mainapp.NekoRezervisaoRequest.class,
      responseType = com.xml2023.mainapp.NekoRezervisaoResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.xml2023.mainapp.NekoRezervisaoRequest,
      com.xml2023.mainapp.NekoRezervisaoResponse> getNewRezNotifyMethod() {
    io.grpc.MethodDescriptor<com.xml2023.mainapp.NekoRezervisaoRequest, com.xml2023.mainapp.NekoRezervisaoResponse> getNewRezNotifyMethod;
    if ((getNewRezNotifyMethod = KorisnikGrpcGrpc.getNewRezNotifyMethod) == null) {
      synchronized (KorisnikGrpcGrpc.class) {
        if ((getNewRezNotifyMethod = KorisnikGrpcGrpc.getNewRezNotifyMethod) == null) {
          KorisnikGrpcGrpc.getNewRezNotifyMethod = getNewRezNotifyMethod = 
              io.grpc.MethodDescriptor.<com.xml2023.mainapp.NekoRezervisaoRequest, com.xml2023.mainapp.NekoRezervisaoResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.xml2023.mainapp.KorisnikGrpc", "newRezNotify"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xml2023.mainapp.NekoRezervisaoRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xml2023.mainapp.NekoRezervisaoResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new KorisnikGrpcMethodDescriptorSupplier("newRezNotify"))
                  .build();
          }
        }
     }
     return getNewRezNotifyMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.xml2023.mainapp.NekoOtkazaoRequest,
      com.xml2023.mainapp.NekoOtkazaoResponse> getNewQuitNotifyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "newQuitNotify",
      requestType = com.xml2023.mainapp.NekoOtkazaoRequest.class,
      responseType = com.xml2023.mainapp.NekoOtkazaoResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.xml2023.mainapp.NekoOtkazaoRequest,
      com.xml2023.mainapp.NekoOtkazaoResponse> getNewQuitNotifyMethod() {
    io.grpc.MethodDescriptor<com.xml2023.mainapp.NekoOtkazaoRequest, com.xml2023.mainapp.NekoOtkazaoResponse> getNewQuitNotifyMethod;
    if ((getNewQuitNotifyMethod = KorisnikGrpcGrpc.getNewQuitNotifyMethod) == null) {
      synchronized (KorisnikGrpcGrpc.class) {
        if ((getNewQuitNotifyMethod = KorisnikGrpcGrpc.getNewQuitNotifyMethod) == null) {
          KorisnikGrpcGrpc.getNewQuitNotifyMethod = getNewQuitNotifyMethod = 
              io.grpc.MethodDescriptor.<com.xml2023.mainapp.NekoOtkazaoRequest, com.xml2023.mainapp.NekoOtkazaoResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.xml2023.mainapp.KorisnikGrpc", "newQuitNotify"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xml2023.mainapp.NekoOtkazaoRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xml2023.mainapp.NekoOtkazaoResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new KorisnikGrpcMethodDescriptorSupplier("newQuitNotify"))
                  .build();
          }
        }
     }
     return getNewQuitNotifyMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.xml2023.mainapp.NekoOcenioSmestajRequest,
      com.xml2023.mainapp.NekoOcenioSmestajResponse> getNewRankSmestajMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "newRankSmestaj",
      requestType = com.xml2023.mainapp.NekoOcenioSmestajRequest.class,
      responseType = com.xml2023.mainapp.NekoOcenioSmestajResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.xml2023.mainapp.NekoOcenioSmestajRequest,
      com.xml2023.mainapp.NekoOcenioSmestajResponse> getNewRankSmestajMethod() {
    io.grpc.MethodDescriptor<com.xml2023.mainapp.NekoOcenioSmestajRequest, com.xml2023.mainapp.NekoOcenioSmestajResponse> getNewRankSmestajMethod;
    if ((getNewRankSmestajMethod = KorisnikGrpcGrpc.getNewRankSmestajMethod) == null) {
      synchronized (KorisnikGrpcGrpc.class) {
        if ((getNewRankSmestajMethod = KorisnikGrpcGrpc.getNewRankSmestajMethod) == null) {
          KorisnikGrpcGrpc.getNewRankSmestajMethod = getNewRankSmestajMethod = 
              io.grpc.MethodDescriptor.<com.xml2023.mainapp.NekoOcenioSmestajRequest, com.xml2023.mainapp.NekoOcenioSmestajResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.xml2023.mainapp.KorisnikGrpc", "newRankSmestaj"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xml2023.mainapp.NekoOcenioSmestajRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xml2023.mainapp.NekoOcenioSmestajResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new KorisnikGrpcMethodDescriptorSupplier("newRankSmestaj"))
                  .build();
          }
        }
     }
     return getNewRankSmestajMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.xml2023.mainapp.NekoOcenioHostaRequest,
      com.xml2023.mainapp.NekoOcenioHostaResponse> getNewRankHostMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "newRankHost",
      requestType = com.xml2023.mainapp.NekoOcenioHostaRequest.class,
      responseType = com.xml2023.mainapp.NekoOcenioHostaResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.xml2023.mainapp.NekoOcenioHostaRequest,
      com.xml2023.mainapp.NekoOcenioHostaResponse> getNewRankHostMethod() {
    io.grpc.MethodDescriptor<com.xml2023.mainapp.NekoOcenioHostaRequest, com.xml2023.mainapp.NekoOcenioHostaResponse> getNewRankHostMethod;
    if ((getNewRankHostMethod = KorisnikGrpcGrpc.getNewRankHostMethod) == null) {
      synchronized (KorisnikGrpcGrpc.class) {
        if ((getNewRankHostMethod = KorisnikGrpcGrpc.getNewRankHostMethod) == null) {
          KorisnikGrpcGrpc.getNewRankHostMethod = getNewRankHostMethod = 
              io.grpc.MethodDescriptor.<com.xml2023.mainapp.NekoOcenioHostaRequest, com.xml2023.mainapp.NekoOcenioHostaResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.xml2023.mainapp.KorisnikGrpc", "newRankHost"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xml2023.mainapp.NekoOcenioHostaRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xml2023.mainapp.NekoOcenioHostaResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new KorisnikGrpcMethodDescriptorSupplier("newRankHost"))
                  .build();
          }
        }
     }
     return getNewRankHostMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.xml2023.mainapp.DobioStatusIstaknutogRequest,
      com.xml2023.mainapp.DobioStatusIstaknutogResponse> getIstaknutiHostMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "istaknutiHost",
      requestType = com.xml2023.mainapp.DobioStatusIstaknutogRequest.class,
      responseType = com.xml2023.mainapp.DobioStatusIstaknutogResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.xml2023.mainapp.DobioStatusIstaknutogRequest,
      com.xml2023.mainapp.DobioStatusIstaknutogResponse> getIstaknutiHostMethod() {
    io.grpc.MethodDescriptor<com.xml2023.mainapp.DobioStatusIstaknutogRequest, com.xml2023.mainapp.DobioStatusIstaknutogResponse> getIstaknutiHostMethod;
    if ((getIstaknutiHostMethod = KorisnikGrpcGrpc.getIstaknutiHostMethod) == null) {
      synchronized (KorisnikGrpcGrpc.class) {
        if ((getIstaknutiHostMethod = KorisnikGrpcGrpc.getIstaknutiHostMethod) == null) {
          KorisnikGrpcGrpc.getIstaknutiHostMethod = getIstaknutiHostMethod = 
              io.grpc.MethodDescriptor.<com.xml2023.mainapp.DobioStatusIstaknutogRequest, com.xml2023.mainapp.DobioStatusIstaknutogResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.xml2023.mainapp.KorisnikGrpc", "istaknutiHost"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xml2023.mainapp.DobioStatusIstaknutogRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xml2023.mainapp.DobioStatusIstaknutogResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new KorisnikGrpcMethodDescriptorSupplier("istaknutiHost"))
                  .build();
          }
        }
     }
     return getIstaknutiHostMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.xml2023.mainapp.NovaRezervacijaNotifikacijaRequest,
      com.xml2023.mainapp.NovaRezervacijaNotifikacijaResponse> getNovaRezNotStatusMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "novaRezNotStatus",
      requestType = com.xml2023.mainapp.NovaRezervacijaNotifikacijaRequest.class,
      responseType = com.xml2023.mainapp.NovaRezervacijaNotifikacijaResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.xml2023.mainapp.NovaRezervacijaNotifikacijaRequest,
      com.xml2023.mainapp.NovaRezervacijaNotifikacijaResponse> getNovaRezNotStatusMethod() {
    io.grpc.MethodDescriptor<com.xml2023.mainapp.NovaRezervacijaNotifikacijaRequest, com.xml2023.mainapp.NovaRezervacijaNotifikacijaResponse> getNovaRezNotStatusMethod;
    if ((getNovaRezNotStatusMethod = KorisnikGrpcGrpc.getNovaRezNotStatusMethod) == null) {
      synchronized (KorisnikGrpcGrpc.class) {
        if ((getNovaRezNotStatusMethod = KorisnikGrpcGrpc.getNovaRezNotStatusMethod) == null) {
          KorisnikGrpcGrpc.getNovaRezNotStatusMethod = getNovaRezNotStatusMethod = 
              io.grpc.MethodDescriptor.<com.xml2023.mainapp.NovaRezervacijaNotifikacijaRequest, com.xml2023.mainapp.NovaRezervacijaNotifikacijaResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.xml2023.mainapp.KorisnikGrpc", "novaRezNotStatus"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xml2023.mainapp.NovaRezervacijaNotifikacijaRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xml2023.mainapp.NovaRezervacijaNotifikacijaResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new KorisnikGrpcMethodDescriptorSupplier("novaRezNotStatus"))
                  .build();
          }
        }
     }
     return getNovaRezNotStatusMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.xml2023.mainapp.OtkazanaRezervacijaNotifikacijaRequest,
      com.xml2023.mainapp.OtkazanaRezervacijaNotifikacijaResponse> getOtkazanaRezNotStatusMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "otkazanaRezNotStatus",
      requestType = com.xml2023.mainapp.OtkazanaRezervacijaNotifikacijaRequest.class,
      responseType = com.xml2023.mainapp.OtkazanaRezervacijaNotifikacijaResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.xml2023.mainapp.OtkazanaRezervacijaNotifikacijaRequest,
      com.xml2023.mainapp.OtkazanaRezervacijaNotifikacijaResponse> getOtkazanaRezNotStatusMethod() {
    io.grpc.MethodDescriptor<com.xml2023.mainapp.OtkazanaRezervacijaNotifikacijaRequest, com.xml2023.mainapp.OtkazanaRezervacijaNotifikacijaResponse> getOtkazanaRezNotStatusMethod;
    if ((getOtkazanaRezNotStatusMethod = KorisnikGrpcGrpc.getOtkazanaRezNotStatusMethod) == null) {
      synchronized (KorisnikGrpcGrpc.class) {
        if ((getOtkazanaRezNotStatusMethod = KorisnikGrpcGrpc.getOtkazanaRezNotStatusMethod) == null) {
          KorisnikGrpcGrpc.getOtkazanaRezNotStatusMethod = getOtkazanaRezNotStatusMethod = 
              io.grpc.MethodDescriptor.<com.xml2023.mainapp.OtkazanaRezervacijaNotifikacijaRequest, com.xml2023.mainapp.OtkazanaRezervacijaNotifikacijaResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.xml2023.mainapp.KorisnikGrpc", "otkazanaRezNotStatus"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xml2023.mainapp.OtkazanaRezervacijaNotifikacijaRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xml2023.mainapp.OtkazanaRezervacijaNotifikacijaResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new KorisnikGrpcMethodDescriptorSupplier("otkazanaRezNotStatus"))
                  .build();
          }
        }
     }
     return getOtkazanaRezNotStatusMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.xml2023.mainapp.NovaOcenaHostaNotifikacijaRequest,
      com.xml2023.mainapp.NovaOcenaHostaNotifikacijaResponse> getNovaOcenaHostaNotStatusMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "novaOcenaHostaNotStatus",
      requestType = com.xml2023.mainapp.NovaOcenaHostaNotifikacijaRequest.class,
      responseType = com.xml2023.mainapp.NovaOcenaHostaNotifikacijaResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.xml2023.mainapp.NovaOcenaHostaNotifikacijaRequest,
      com.xml2023.mainapp.NovaOcenaHostaNotifikacijaResponse> getNovaOcenaHostaNotStatusMethod() {
    io.grpc.MethodDescriptor<com.xml2023.mainapp.NovaOcenaHostaNotifikacijaRequest, com.xml2023.mainapp.NovaOcenaHostaNotifikacijaResponse> getNovaOcenaHostaNotStatusMethod;
    if ((getNovaOcenaHostaNotStatusMethod = KorisnikGrpcGrpc.getNovaOcenaHostaNotStatusMethod) == null) {
      synchronized (KorisnikGrpcGrpc.class) {
        if ((getNovaOcenaHostaNotStatusMethod = KorisnikGrpcGrpc.getNovaOcenaHostaNotStatusMethod) == null) {
          KorisnikGrpcGrpc.getNovaOcenaHostaNotStatusMethod = getNovaOcenaHostaNotStatusMethod = 
              io.grpc.MethodDescriptor.<com.xml2023.mainapp.NovaOcenaHostaNotifikacijaRequest, com.xml2023.mainapp.NovaOcenaHostaNotifikacijaResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.xml2023.mainapp.KorisnikGrpc", "novaOcenaHostaNotStatus"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xml2023.mainapp.NovaOcenaHostaNotifikacijaRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xml2023.mainapp.NovaOcenaHostaNotifikacijaResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new KorisnikGrpcMethodDescriptorSupplier("novaOcenaHostaNotStatus"))
                  .build();
          }
        }
     }
     return getNovaOcenaHostaNotStatusMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.xml2023.mainapp.NovaOcenaSmestajaNotifikacijaRequest,
      com.xml2023.mainapp.NovaOcenaSmestajaNotifikacijaResponse> getNovaOcenaSmestajaNotStatusMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "novaOcenaSmestajaNotStatus",
      requestType = com.xml2023.mainapp.NovaOcenaSmestajaNotifikacijaRequest.class,
      responseType = com.xml2023.mainapp.NovaOcenaSmestajaNotifikacijaResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.xml2023.mainapp.NovaOcenaSmestajaNotifikacijaRequest,
      com.xml2023.mainapp.NovaOcenaSmestajaNotifikacijaResponse> getNovaOcenaSmestajaNotStatusMethod() {
    io.grpc.MethodDescriptor<com.xml2023.mainapp.NovaOcenaSmestajaNotifikacijaRequest, com.xml2023.mainapp.NovaOcenaSmestajaNotifikacijaResponse> getNovaOcenaSmestajaNotStatusMethod;
    if ((getNovaOcenaSmestajaNotStatusMethod = KorisnikGrpcGrpc.getNovaOcenaSmestajaNotStatusMethod) == null) {
      synchronized (KorisnikGrpcGrpc.class) {
        if ((getNovaOcenaSmestajaNotStatusMethod = KorisnikGrpcGrpc.getNovaOcenaSmestajaNotStatusMethod) == null) {
          KorisnikGrpcGrpc.getNovaOcenaSmestajaNotStatusMethod = getNovaOcenaSmestajaNotStatusMethod = 
              io.grpc.MethodDescriptor.<com.xml2023.mainapp.NovaOcenaSmestajaNotifikacijaRequest, com.xml2023.mainapp.NovaOcenaSmestajaNotifikacijaResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.xml2023.mainapp.KorisnikGrpc", "novaOcenaSmestajaNotStatus"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xml2023.mainapp.NovaOcenaSmestajaNotifikacijaRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xml2023.mainapp.NovaOcenaSmestajaNotifikacijaResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new KorisnikGrpcMethodDescriptorSupplier("novaOcenaSmestajaNotStatus"))
                  .build();
          }
        }
     }
     return getNovaOcenaSmestajaNotStatusMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.xml2023.mainapp.StatusIstaknutogNotifikacijaRequest,
      com.xml2023.mainapp.StatusIstaknutogNotifikacijaResponse> getIstaknutiNotStatusMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "istaknutiNotStatus",
      requestType = com.xml2023.mainapp.StatusIstaknutogNotifikacijaRequest.class,
      responseType = com.xml2023.mainapp.StatusIstaknutogNotifikacijaResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.xml2023.mainapp.StatusIstaknutogNotifikacijaRequest,
      com.xml2023.mainapp.StatusIstaknutogNotifikacijaResponse> getIstaknutiNotStatusMethod() {
    io.grpc.MethodDescriptor<com.xml2023.mainapp.StatusIstaknutogNotifikacijaRequest, com.xml2023.mainapp.StatusIstaknutogNotifikacijaResponse> getIstaknutiNotStatusMethod;
    if ((getIstaknutiNotStatusMethod = KorisnikGrpcGrpc.getIstaknutiNotStatusMethod) == null) {
      synchronized (KorisnikGrpcGrpc.class) {
        if ((getIstaknutiNotStatusMethod = KorisnikGrpcGrpc.getIstaknutiNotStatusMethod) == null) {
          KorisnikGrpcGrpc.getIstaknutiNotStatusMethod = getIstaknutiNotStatusMethod = 
              io.grpc.MethodDescriptor.<com.xml2023.mainapp.StatusIstaknutogNotifikacijaRequest, com.xml2023.mainapp.StatusIstaknutogNotifikacijaResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.xml2023.mainapp.KorisnikGrpc", "istaknutiNotStatus"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xml2023.mainapp.StatusIstaknutogNotifikacijaRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xml2023.mainapp.StatusIstaknutogNotifikacijaResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new KorisnikGrpcMethodDescriptorSupplier("istaknutiNotStatus"))
                  .build();
          }
        }
     }
     return getIstaknutiNotStatusMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static KorisnikGrpcStub newStub(io.grpc.Channel channel) {
    return new KorisnikGrpcStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static KorisnikGrpcBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new KorisnikGrpcBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static KorisnikGrpcFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new KorisnikGrpcFutureStub(channel);
  }

  /**
   */
  public static abstract class KorisnikGrpcImplBase implements io.grpc.BindableService {

    /**
     */
    public void getHost(com.xml2023.mainapp.getHostRequest request,
        io.grpc.stub.StreamObserver<com.xml2023.mainapp.getHostResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetHostMethod(), responseObserver);
    }

    /**
     */
    public void rezOtkazana(com.xml2023.mainapp.rezOtkazanaRequest request,
        io.grpc.stub.StreamObserver<com.xml2023.mainapp.rezOtkazanaResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRezOtkazanaMethod(), responseObserver);
    }

    /**
     */
    public void rezOtkazanaHost(com.xml2023.mainapp.rezOtkazanaHostRequest request,
        io.grpc.stub.StreamObserver<com.xml2023.mainapp.rezOtkazanaHostResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRezOtkazanaHostMethod(), responseObserver);
    }

    /**
     */
    public void rezObavestenje(com.xml2023.mainapp.reservationApprovedNotificationRequest request,
        io.grpc.stub.StreamObserver<com.xml2023.mainapp.reservationApprovedNotificationResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRezObavestenjeMethod(), responseObserver);
    }

    /**
     */
    public void newRezNotify(com.xml2023.mainapp.NekoRezervisaoRequest request,
        io.grpc.stub.StreamObserver<com.xml2023.mainapp.NekoRezervisaoResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getNewRezNotifyMethod(), responseObserver);
    }

    /**
     */
    public void newQuitNotify(com.xml2023.mainapp.NekoOtkazaoRequest request,
        io.grpc.stub.StreamObserver<com.xml2023.mainapp.NekoOtkazaoResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getNewQuitNotifyMethod(), responseObserver);
    }

    /**
     */
    public void newRankSmestaj(com.xml2023.mainapp.NekoOcenioSmestajRequest request,
        io.grpc.stub.StreamObserver<com.xml2023.mainapp.NekoOcenioSmestajResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getNewRankSmestajMethod(), responseObserver);
    }

    /**
     */
    public void newRankHost(com.xml2023.mainapp.NekoOcenioHostaRequest request,
        io.grpc.stub.StreamObserver<com.xml2023.mainapp.NekoOcenioHostaResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getNewRankHostMethod(), responseObserver);
    }

    /**
     */
    public void istaknutiHost(com.xml2023.mainapp.DobioStatusIstaknutogRequest request,
        io.grpc.stub.StreamObserver<com.xml2023.mainapp.DobioStatusIstaknutogResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getIstaknutiHostMethod(), responseObserver);
    }

    /**
     */
    public void novaRezNotStatus(com.xml2023.mainapp.NovaRezervacijaNotifikacijaRequest request,
        io.grpc.stub.StreamObserver<com.xml2023.mainapp.NovaRezervacijaNotifikacijaResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getNovaRezNotStatusMethod(), responseObserver);
    }

    /**
     */
    public void otkazanaRezNotStatus(com.xml2023.mainapp.OtkazanaRezervacijaNotifikacijaRequest request,
        io.grpc.stub.StreamObserver<com.xml2023.mainapp.OtkazanaRezervacijaNotifikacijaResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getOtkazanaRezNotStatusMethod(), responseObserver);
    }

    /**
     */
    public void novaOcenaHostaNotStatus(com.xml2023.mainapp.NovaOcenaHostaNotifikacijaRequest request,
        io.grpc.stub.StreamObserver<com.xml2023.mainapp.NovaOcenaHostaNotifikacijaResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getNovaOcenaHostaNotStatusMethod(), responseObserver);
    }

    /**
     */
    public void novaOcenaSmestajaNotStatus(com.xml2023.mainapp.NovaOcenaSmestajaNotifikacijaRequest request,
        io.grpc.stub.StreamObserver<com.xml2023.mainapp.NovaOcenaSmestajaNotifikacijaResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getNovaOcenaSmestajaNotStatusMethod(), responseObserver);
    }

    /**
     */
    public void istaknutiNotStatus(com.xml2023.mainapp.StatusIstaknutogNotifikacijaRequest request,
        io.grpc.stub.StreamObserver<com.xml2023.mainapp.StatusIstaknutogNotifikacijaResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getIstaknutiNotStatusMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetHostMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.xml2023.mainapp.getHostRequest,
                com.xml2023.mainapp.getHostResponse>(
                  this, METHODID_GET_HOST)))
          .addMethod(
            getRezOtkazanaMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.xml2023.mainapp.rezOtkazanaRequest,
                com.xml2023.mainapp.rezOtkazanaResponse>(
                  this, METHODID_REZ_OTKAZANA)))
          .addMethod(
            getRezOtkazanaHostMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.xml2023.mainapp.rezOtkazanaHostRequest,
                com.xml2023.mainapp.rezOtkazanaHostResponse>(
                  this, METHODID_REZ_OTKAZANA_HOST)))
          .addMethod(
            getRezObavestenjeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.xml2023.mainapp.reservationApprovedNotificationRequest,
                com.xml2023.mainapp.reservationApprovedNotificationResponse>(
                  this, METHODID_REZ_OBAVESTENJE)))
          .addMethod(
            getNewRezNotifyMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.xml2023.mainapp.NekoRezervisaoRequest,
                com.xml2023.mainapp.NekoRezervisaoResponse>(
                  this, METHODID_NEW_REZ_NOTIFY)))
          .addMethod(
            getNewQuitNotifyMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.xml2023.mainapp.NekoOtkazaoRequest,
                com.xml2023.mainapp.NekoOtkazaoResponse>(
                  this, METHODID_NEW_QUIT_NOTIFY)))
          .addMethod(
            getNewRankSmestajMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.xml2023.mainapp.NekoOcenioSmestajRequest,
                com.xml2023.mainapp.NekoOcenioSmestajResponse>(
                  this, METHODID_NEW_RANK_SMESTAJ)))
          .addMethod(
            getNewRankHostMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.xml2023.mainapp.NekoOcenioHostaRequest,
                com.xml2023.mainapp.NekoOcenioHostaResponse>(
                  this, METHODID_NEW_RANK_HOST)))
          .addMethod(
            getIstaknutiHostMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.xml2023.mainapp.DobioStatusIstaknutogRequest,
                com.xml2023.mainapp.DobioStatusIstaknutogResponse>(
                  this, METHODID_ISTAKNUTI_HOST)))
          .addMethod(
            getNovaRezNotStatusMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.xml2023.mainapp.NovaRezervacijaNotifikacijaRequest,
                com.xml2023.mainapp.NovaRezervacijaNotifikacijaResponse>(
                  this, METHODID_NOVA_REZ_NOT_STATUS)))
          .addMethod(
            getOtkazanaRezNotStatusMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.xml2023.mainapp.OtkazanaRezervacijaNotifikacijaRequest,
                com.xml2023.mainapp.OtkazanaRezervacijaNotifikacijaResponse>(
                  this, METHODID_OTKAZANA_REZ_NOT_STATUS)))
          .addMethod(
            getNovaOcenaHostaNotStatusMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.xml2023.mainapp.NovaOcenaHostaNotifikacijaRequest,
                com.xml2023.mainapp.NovaOcenaHostaNotifikacijaResponse>(
                  this, METHODID_NOVA_OCENA_HOSTA_NOT_STATUS)))
          .addMethod(
            getNovaOcenaSmestajaNotStatusMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.xml2023.mainapp.NovaOcenaSmestajaNotifikacijaRequest,
                com.xml2023.mainapp.NovaOcenaSmestajaNotifikacijaResponse>(
                  this, METHODID_NOVA_OCENA_SMESTAJA_NOT_STATUS)))
          .addMethod(
            getIstaknutiNotStatusMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.xml2023.mainapp.StatusIstaknutogNotifikacijaRequest,
                com.xml2023.mainapp.StatusIstaknutogNotifikacijaResponse>(
                  this, METHODID_ISTAKNUTI_NOT_STATUS)))
          .build();
    }
  }

  /**
   */
  public static final class KorisnikGrpcStub extends io.grpc.stub.AbstractStub<KorisnikGrpcStub> {
    private KorisnikGrpcStub(io.grpc.Channel channel) {
      super(channel);
    }

    private KorisnikGrpcStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KorisnikGrpcStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new KorisnikGrpcStub(channel, callOptions);
    }

    /**
     */
    public void getHost(com.xml2023.mainapp.getHostRequest request,
        io.grpc.stub.StreamObserver<com.xml2023.mainapp.getHostResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetHostMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void rezOtkazana(com.xml2023.mainapp.rezOtkazanaRequest request,
        io.grpc.stub.StreamObserver<com.xml2023.mainapp.rezOtkazanaResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRezOtkazanaMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void rezOtkazanaHost(com.xml2023.mainapp.rezOtkazanaHostRequest request,
        io.grpc.stub.StreamObserver<com.xml2023.mainapp.rezOtkazanaHostResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRezOtkazanaHostMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void rezObavestenje(com.xml2023.mainapp.reservationApprovedNotificationRequest request,
        io.grpc.stub.StreamObserver<com.xml2023.mainapp.reservationApprovedNotificationResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRezObavestenjeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void newRezNotify(com.xml2023.mainapp.NekoRezervisaoRequest request,
        io.grpc.stub.StreamObserver<com.xml2023.mainapp.NekoRezervisaoResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getNewRezNotifyMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void newQuitNotify(com.xml2023.mainapp.NekoOtkazaoRequest request,
        io.grpc.stub.StreamObserver<com.xml2023.mainapp.NekoOtkazaoResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getNewQuitNotifyMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void newRankSmestaj(com.xml2023.mainapp.NekoOcenioSmestajRequest request,
        io.grpc.stub.StreamObserver<com.xml2023.mainapp.NekoOcenioSmestajResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getNewRankSmestajMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void newRankHost(com.xml2023.mainapp.NekoOcenioHostaRequest request,
        io.grpc.stub.StreamObserver<com.xml2023.mainapp.NekoOcenioHostaResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getNewRankHostMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void istaknutiHost(com.xml2023.mainapp.DobioStatusIstaknutogRequest request,
        io.grpc.stub.StreamObserver<com.xml2023.mainapp.DobioStatusIstaknutogResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getIstaknutiHostMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void novaRezNotStatus(com.xml2023.mainapp.NovaRezervacijaNotifikacijaRequest request,
        io.grpc.stub.StreamObserver<com.xml2023.mainapp.NovaRezervacijaNotifikacijaResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getNovaRezNotStatusMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void otkazanaRezNotStatus(com.xml2023.mainapp.OtkazanaRezervacijaNotifikacijaRequest request,
        io.grpc.stub.StreamObserver<com.xml2023.mainapp.OtkazanaRezervacijaNotifikacijaResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getOtkazanaRezNotStatusMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void novaOcenaHostaNotStatus(com.xml2023.mainapp.NovaOcenaHostaNotifikacijaRequest request,
        io.grpc.stub.StreamObserver<com.xml2023.mainapp.NovaOcenaHostaNotifikacijaResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getNovaOcenaHostaNotStatusMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void novaOcenaSmestajaNotStatus(com.xml2023.mainapp.NovaOcenaSmestajaNotifikacijaRequest request,
        io.grpc.stub.StreamObserver<com.xml2023.mainapp.NovaOcenaSmestajaNotifikacijaResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getNovaOcenaSmestajaNotStatusMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void istaknutiNotStatus(com.xml2023.mainapp.StatusIstaknutogNotifikacijaRequest request,
        io.grpc.stub.StreamObserver<com.xml2023.mainapp.StatusIstaknutogNotifikacijaResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getIstaknutiNotStatusMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class KorisnikGrpcBlockingStub extends io.grpc.stub.AbstractStub<KorisnikGrpcBlockingStub> {
    private KorisnikGrpcBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private KorisnikGrpcBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KorisnikGrpcBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new KorisnikGrpcBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.xml2023.mainapp.getHostResponse getHost(com.xml2023.mainapp.getHostRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetHostMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.xml2023.mainapp.rezOtkazanaResponse rezOtkazana(com.xml2023.mainapp.rezOtkazanaRequest request) {
      return blockingUnaryCall(
          getChannel(), getRezOtkazanaMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.xml2023.mainapp.rezOtkazanaHostResponse rezOtkazanaHost(com.xml2023.mainapp.rezOtkazanaHostRequest request) {
      return blockingUnaryCall(
          getChannel(), getRezOtkazanaHostMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.xml2023.mainapp.reservationApprovedNotificationResponse rezObavestenje(com.xml2023.mainapp.reservationApprovedNotificationRequest request) {
      return blockingUnaryCall(
          getChannel(), getRezObavestenjeMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.xml2023.mainapp.NekoRezervisaoResponse newRezNotify(com.xml2023.mainapp.NekoRezervisaoRequest request) {
      return blockingUnaryCall(
          getChannel(), getNewRezNotifyMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.xml2023.mainapp.NekoOtkazaoResponse newQuitNotify(com.xml2023.mainapp.NekoOtkazaoRequest request) {
      return blockingUnaryCall(
          getChannel(), getNewQuitNotifyMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.xml2023.mainapp.NekoOcenioSmestajResponse newRankSmestaj(com.xml2023.mainapp.NekoOcenioSmestajRequest request) {
      return blockingUnaryCall(
          getChannel(), getNewRankSmestajMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.xml2023.mainapp.NekoOcenioHostaResponse newRankHost(com.xml2023.mainapp.NekoOcenioHostaRequest request) {
      return blockingUnaryCall(
          getChannel(), getNewRankHostMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.xml2023.mainapp.DobioStatusIstaknutogResponse istaknutiHost(com.xml2023.mainapp.DobioStatusIstaknutogRequest request) {
      return blockingUnaryCall(
          getChannel(), getIstaknutiHostMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.xml2023.mainapp.NovaRezervacijaNotifikacijaResponse novaRezNotStatus(com.xml2023.mainapp.NovaRezervacijaNotifikacijaRequest request) {
      return blockingUnaryCall(
          getChannel(), getNovaRezNotStatusMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.xml2023.mainapp.OtkazanaRezervacijaNotifikacijaResponse otkazanaRezNotStatus(com.xml2023.mainapp.OtkazanaRezervacijaNotifikacijaRequest request) {
      return blockingUnaryCall(
          getChannel(), getOtkazanaRezNotStatusMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.xml2023.mainapp.NovaOcenaHostaNotifikacijaResponse novaOcenaHostaNotStatus(com.xml2023.mainapp.NovaOcenaHostaNotifikacijaRequest request) {
      return blockingUnaryCall(
          getChannel(), getNovaOcenaHostaNotStatusMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.xml2023.mainapp.NovaOcenaSmestajaNotifikacijaResponse novaOcenaSmestajaNotStatus(com.xml2023.mainapp.NovaOcenaSmestajaNotifikacijaRequest request) {
      return blockingUnaryCall(
          getChannel(), getNovaOcenaSmestajaNotStatusMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.xml2023.mainapp.StatusIstaknutogNotifikacijaResponse istaknutiNotStatus(com.xml2023.mainapp.StatusIstaknutogNotifikacijaRequest request) {
      return blockingUnaryCall(
          getChannel(), getIstaknutiNotStatusMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class KorisnikGrpcFutureStub extends io.grpc.stub.AbstractStub<KorisnikGrpcFutureStub> {
    private KorisnikGrpcFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private KorisnikGrpcFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KorisnikGrpcFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new KorisnikGrpcFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.xml2023.mainapp.getHostResponse> getHost(
        com.xml2023.mainapp.getHostRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetHostMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.xml2023.mainapp.rezOtkazanaResponse> rezOtkazana(
        com.xml2023.mainapp.rezOtkazanaRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getRezOtkazanaMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.xml2023.mainapp.rezOtkazanaHostResponse> rezOtkazanaHost(
        com.xml2023.mainapp.rezOtkazanaHostRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getRezOtkazanaHostMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.xml2023.mainapp.reservationApprovedNotificationResponse> rezObavestenje(
        com.xml2023.mainapp.reservationApprovedNotificationRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getRezObavestenjeMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.xml2023.mainapp.NekoRezervisaoResponse> newRezNotify(
        com.xml2023.mainapp.NekoRezervisaoRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getNewRezNotifyMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.xml2023.mainapp.NekoOtkazaoResponse> newQuitNotify(
        com.xml2023.mainapp.NekoOtkazaoRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getNewQuitNotifyMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.xml2023.mainapp.NekoOcenioSmestajResponse> newRankSmestaj(
        com.xml2023.mainapp.NekoOcenioSmestajRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getNewRankSmestajMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.xml2023.mainapp.NekoOcenioHostaResponse> newRankHost(
        com.xml2023.mainapp.NekoOcenioHostaRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getNewRankHostMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.xml2023.mainapp.DobioStatusIstaknutogResponse> istaknutiHost(
        com.xml2023.mainapp.DobioStatusIstaknutogRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getIstaknutiHostMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.xml2023.mainapp.NovaRezervacijaNotifikacijaResponse> novaRezNotStatus(
        com.xml2023.mainapp.NovaRezervacijaNotifikacijaRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getNovaRezNotStatusMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.xml2023.mainapp.OtkazanaRezervacijaNotifikacijaResponse> otkazanaRezNotStatus(
        com.xml2023.mainapp.OtkazanaRezervacijaNotifikacijaRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getOtkazanaRezNotStatusMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.xml2023.mainapp.NovaOcenaHostaNotifikacijaResponse> novaOcenaHostaNotStatus(
        com.xml2023.mainapp.NovaOcenaHostaNotifikacijaRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getNovaOcenaHostaNotStatusMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.xml2023.mainapp.NovaOcenaSmestajaNotifikacijaResponse> novaOcenaSmestajaNotStatus(
        com.xml2023.mainapp.NovaOcenaSmestajaNotifikacijaRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getNovaOcenaSmestajaNotStatusMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.xml2023.mainapp.StatusIstaknutogNotifikacijaResponse> istaknutiNotStatus(
        com.xml2023.mainapp.StatusIstaknutogNotifikacijaRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getIstaknutiNotStatusMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_HOST = 0;
  private static final int METHODID_REZ_OTKAZANA = 1;
  private static final int METHODID_REZ_OTKAZANA_HOST = 2;
  private static final int METHODID_REZ_OBAVESTENJE = 3;
  private static final int METHODID_NEW_REZ_NOTIFY = 4;
  private static final int METHODID_NEW_QUIT_NOTIFY = 5;
  private static final int METHODID_NEW_RANK_SMESTAJ = 6;
  private static final int METHODID_NEW_RANK_HOST = 7;
  private static final int METHODID_ISTAKNUTI_HOST = 8;
  private static final int METHODID_NOVA_REZ_NOT_STATUS = 9;
  private static final int METHODID_OTKAZANA_REZ_NOT_STATUS = 10;
  private static final int METHODID_NOVA_OCENA_HOSTA_NOT_STATUS = 11;
  private static final int METHODID_NOVA_OCENA_SMESTAJA_NOT_STATUS = 12;
  private static final int METHODID_ISTAKNUTI_NOT_STATUS = 13;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final KorisnikGrpcImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(KorisnikGrpcImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_HOST:
          serviceImpl.getHost((com.xml2023.mainapp.getHostRequest) request,
              (io.grpc.stub.StreamObserver<com.xml2023.mainapp.getHostResponse>) responseObserver);
          break;
        case METHODID_REZ_OTKAZANA:
          serviceImpl.rezOtkazana((com.xml2023.mainapp.rezOtkazanaRequest) request,
              (io.grpc.stub.StreamObserver<com.xml2023.mainapp.rezOtkazanaResponse>) responseObserver);
          break;
        case METHODID_REZ_OTKAZANA_HOST:
          serviceImpl.rezOtkazanaHost((com.xml2023.mainapp.rezOtkazanaHostRequest) request,
              (io.grpc.stub.StreamObserver<com.xml2023.mainapp.rezOtkazanaHostResponse>) responseObserver);
          break;
        case METHODID_REZ_OBAVESTENJE:
          serviceImpl.rezObavestenje((com.xml2023.mainapp.reservationApprovedNotificationRequest) request,
              (io.grpc.stub.StreamObserver<com.xml2023.mainapp.reservationApprovedNotificationResponse>) responseObserver);
          break;
        case METHODID_NEW_REZ_NOTIFY:
          serviceImpl.newRezNotify((com.xml2023.mainapp.NekoRezervisaoRequest) request,
              (io.grpc.stub.StreamObserver<com.xml2023.mainapp.NekoRezervisaoResponse>) responseObserver);
          break;
        case METHODID_NEW_QUIT_NOTIFY:
          serviceImpl.newQuitNotify((com.xml2023.mainapp.NekoOtkazaoRequest) request,
              (io.grpc.stub.StreamObserver<com.xml2023.mainapp.NekoOtkazaoResponse>) responseObserver);
          break;
        case METHODID_NEW_RANK_SMESTAJ:
          serviceImpl.newRankSmestaj((com.xml2023.mainapp.NekoOcenioSmestajRequest) request,
              (io.grpc.stub.StreamObserver<com.xml2023.mainapp.NekoOcenioSmestajResponse>) responseObserver);
          break;
        case METHODID_NEW_RANK_HOST:
          serviceImpl.newRankHost((com.xml2023.mainapp.NekoOcenioHostaRequest) request,
              (io.grpc.stub.StreamObserver<com.xml2023.mainapp.NekoOcenioHostaResponse>) responseObserver);
          break;
        case METHODID_ISTAKNUTI_HOST:
          serviceImpl.istaknutiHost((com.xml2023.mainapp.DobioStatusIstaknutogRequest) request,
              (io.grpc.stub.StreamObserver<com.xml2023.mainapp.DobioStatusIstaknutogResponse>) responseObserver);
          break;
        case METHODID_NOVA_REZ_NOT_STATUS:
          serviceImpl.novaRezNotStatus((com.xml2023.mainapp.NovaRezervacijaNotifikacijaRequest) request,
              (io.grpc.stub.StreamObserver<com.xml2023.mainapp.NovaRezervacijaNotifikacijaResponse>) responseObserver);
          break;
        case METHODID_OTKAZANA_REZ_NOT_STATUS:
          serviceImpl.otkazanaRezNotStatus((com.xml2023.mainapp.OtkazanaRezervacijaNotifikacijaRequest) request,
              (io.grpc.stub.StreamObserver<com.xml2023.mainapp.OtkazanaRezervacijaNotifikacijaResponse>) responseObserver);
          break;
        case METHODID_NOVA_OCENA_HOSTA_NOT_STATUS:
          serviceImpl.novaOcenaHostaNotStatus((com.xml2023.mainapp.NovaOcenaHostaNotifikacijaRequest) request,
              (io.grpc.stub.StreamObserver<com.xml2023.mainapp.NovaOcenaHostaNotifikacijaResponse>) responseObserver);
          break;
        case METHODID_NOVA_OCENA_SMESTAJA_NOT_STATUS:
          serviceImpl.novaOcenaSmestajaNotStatus((com.xml2023.mainapp.NovaOcenaSmestajaNotifikacijaRequest) request,
              (io.grpc.stub.StreamObserver<com.xml2023.mainapp.NovaOcenaSmestajaNotifikacijaResponse>) responseObserver);
          break;
        case METHODID_ISTAKNUTI_NOT_STATUS:
          serviceImpl.istaknutiNotStatus((com.xml2023.mainapp.StatusIstaknutogNotifikacijaRequest) request,
              (io.grpc.stub.StreamObserver<com.xml2023.mainapp.StatusIstaknutogNotifikacijaResponse>) responseObserver);
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

  private static abstract class KorisnikGrpcBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    KorisnikGrpcBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.xml2023.mainapp.Temp.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("KorisnikGrpc");
    }
  }

  private static final class KorisnikGrpcFileDescriptorSupplier
      extends KorisnikGrpcBaseDescriptorSupplier {
    KorisnikGrpcFileDescriptorSupplier() {}
  }

  private static final class KorisnikGrpcMethodDescriptorSupplier
      extends KorisnikGrpcBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    KorisnikGrpcMethodDescriptorSupplier(String methodName) {
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
      synchronized (KorisnikGrpcGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new KorisnikGrpcFileDescriptorSupplier())
              .addMethod(getGetHostMethod())
              .addMethod(getRezOtkazanaMethod())
              .addMethod(getRezOtkazanaHostMethod())
              .addMethod(getRezObavestenjeMethod())
              .addMethod(getNewRezNotifyMethod())
              .addMethod(getNewQuitNotifyMethod())
              .addMethod(getNewRankSmestajMethod())
              .addMethod(getNewRankHostMethod())
              .addMethod(getIstaknutiHostMethod())
              .addMethod(getNovaRezNotStatusMethod())
              .addMethod(getOtkazanaRezNotStatusMethod())
              .addMethod(getNovaOcenaHostaNotStatusMethod())
              .addMethod(getNovaOcenaSmestajaNotStatusMethod())
              .addMethod(getIstaknutiNotStatusMethod())
              .build();
        }
      }
    }
    return result;
  }
}
