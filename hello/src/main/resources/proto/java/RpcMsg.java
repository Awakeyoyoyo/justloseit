// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: rpc.proto

public final class RpcMsg {
  private RpcMsg() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface ErrorResponseOrBuilder extends
      // @@protoc_insertion_point(interface_extends:ErrorResponse)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>int32 errorCode = 1;</code>
     * @return The errorCode.
     */
    int getErrorCode();

    /**
     * <code>repeated string params = 2;</code>
     * @return A list containing the params.
     */
    java.util.List<java.lang.String>
        getParamsList();
    /**
     * <code>repeated string params = 2;</code>
     * @return The count of params.
     */
    int getParamsCount();
    /**
     * <code>repeated string params = 2;</code>
     * @param index The index of the element to return.
     * @return The params at the given index.
     */
    java.lang.String getParams(int index);
    /**
     * <code>repeated string params = 2;</code>
     * @param index The index of the value to return.
     * @return The bytes of the params at the given index.
     */
    com.google.protobuf.ByteString
        getParamsBytes(int index);
  }
  /**
   * Protobuf type {@code ErrorResponse}
   */
  public static final class ErrorResponse extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:ErrorResponse)
      ErrorResponseOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use ErrorResponse.newBuilder() to construct.
    private ErrorResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private ErrorResponse() {
      params_ =
          com.google.protobuf.LazyStringArrayList.emptyList();
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(
        UnusedPrivateParameter unused) {
      return new ErrorResponse();
    }

    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return RpcMsg.internal_static_ErrorResponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return RpcMsg.internal_static_ErrorResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              RpcMsg.ErrorResponse.class, RpcMsg.ErrorResponse.Builder.class);
    }

    public static final int ERRORCODE_FIELD_NUMBER = 1;
    private int errorCode_ = 0;
    /**
     * <code>int32 errorCode = 1;</code>
     * @return The errorCode.
     */
    @java.lang.Override
    public int getErrorCode() {
      return errorCode_;
    }

    public static final int PARAMS_FIELD_NUMBER = 2;
    @SuppressWarnings("serial")
    private com.google.protobuf.LazyStringArrayList params_ =
        com.google.protobuf.LazyStringArrayList.emptyList();
    /**
     * <code>repeated string params = 2;</code>
     * @return A list containing the params.
     */
    public com.google.protobuf.ProtocolStringList
        getParamsList() {
      return params_;
    }
    /**
     * <code>repeated string params = 2;</code>
     * @return The count of params.
     */
    public int getParamsCount() {
      return params_.size();
    }
    /**
     * <code>repeated string params = 2;</code>
     * @param index The index of the element to return.
     * @return The params at the given index.
     */
    public java.lang.String getParams(int index) {
      return params_.get(index);
    }
    /**
     * <code>repeated string params = 2;</code>
     * @param index The index of the value to return.
     * @return The bytes of the params at the given index.
     */
    public com.google.protobuf.ByteString
        getParamsBytes(int index) {
      return params_.getByteString(index);
    }

    private byte memoizedIsInitialized = -1;
    @java.lang.Override
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    @java.lang.Override
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (errorCode_ != 0) {
        output.writeInt32(1, errorCode_);
      }
      for (int i = 0; i < params_.size(); i++) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 2, params_.getRaw(i));
      }
      getUnknownFields().writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (errorCode_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(1, errorCode_);
      }
      {
        int dataSize = 0;
        for (int i = 0; i < params_.size(); i++) {
          dataSize += computeStringSizeNoTag(params_.getRaw(i));
        }
        size += dataSize;
        size += 1 * getParamsList().size();
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof RpcMsg.ErrorResponse)) {
        return super.equals(obj);
      }
      RpcMsg.ErrorResponse other = (RpcMsg.ErrorResponse) obj;

      if (getErrorCode()
          != other.getErrorCode()) return false;
      if (!getParamsList()
          .equals(other.getParamsList())) return false;
      if (!getUnknownFields().equals(other.getUnknownFields())) return false;
      return true;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + ERRORCODE_FIELD_NUMBER;
      hash = (53 * hash) + getErrorCode();
      if (getParamsCount() > 0) {
        hash = (37 * hash) + PARAMS_FIELD_NUMBER;
        hash = (53 * hash) + getParamsList().hashCode();
      }
      hash = (29 * hash) + getUnknownFields().hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static RpcMsg.ErrorResponse parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static RpcMsg.ErrorResponse parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static RpcMsg.ErrorResponse parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static RpcMsg.ErrorResponse parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static RpcMsg.ErrorResponse parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static RpcMsg.ErrorResponse parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static RpcMsg.ErrorResponse parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static RpcMsg.ErrorResponse parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static RpcMsg.ErrorResponse parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }

    public static RpcMsg.ErrorResponse parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static RpcMsg.ErrorResponse parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static RpcMsg.ErrorResponse parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    @java.lang.Override
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(RpcMsg.ErrorResponse prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    @java.lang.Override
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code ErrorResponse}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:ErrorResponse)
        RpcMsg.ErrorResponseOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return RpcMsg.internal_static_ErrorResponse_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return RpcMsg.internal_static_ErrorResponse_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                RpcMsg.ErrorResponse.class, RpcMsg.ErrorResponse.Builder.class);
      }

      // Construct using RpcMsg.ErrorResponse.newBuilder()
      private Builder() {

      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);

      }
      @java.lang.Override
      public Builder clear() {
        super.clear();
        bitField0_ = 0;
        errorCode_ = 0;
        params_ =
            com.google.protobuf.LazyStringArrayList.emptyList();
        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return RpcMsg.internal_static_ErrorResponse_descriptor;
      }

      @java.lang.Override
      public RpcMsg.ErrorResponse getDefaultInstanceForType() {
        return RpcMsg.ErrorResponse.getDefaultInstance();
      }

      @java.lang.Override
      public RpcMsg.ErrorResponse build() {
        RpcMsg.ErrorResponse result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public RpcMsg.ErrorResponse buildPartial() {
        RpcMsg.ErrorResponse result = new RpcMsg.ErrorResponse(this);
        if (bitField0_ != 0) { buildPartial0(result); }
        onBuilt();
        return result;
      }

      private void buildPartial0(RpcMsg.ErrorResponse result) {
        int from_bitField0_ = bitField0_;
        if (((from_bitField0_ & 0x00000001) != 0)) {
          result.errorCode_ = errorCode_;
        }
        if (((from_bitField0_ & 0x00000002) != 0)) {
          params_.makeImmutable();
          result.params_ = params_;
        }
      }

      @java.lang.Override
      public Builder clone() {
        return super.clone();
      }
      @java.lang.Override
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.setField(field, value);
      }
      @java.lang.Override
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return super.clearField(field);
      }
      @java.lang.Override
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return super.clearOneof(oneof);
      }
      @java.lang.Override
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, java.lang.Object value) {
        return super.setRepeatedField(field, index, value);
      }
      @java.lang.Override
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.addRepeatedField(field, value);
      }
      @java.lang.Override
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof RpcMsg.ErrorResponse) {
          return mergeFrom((RpcMsg.ErrorResponse)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(RpcMsg.ErrorResponse other) {
        if (other == RpcMsg.ErrorResponse.getDefaultInstance()) return this;
        if (other.getErrorCode() != 0) {
          setErrorCode(other.getErrorCode());
        }
        if (!other.params_.isEmpty()) {
          if (params_.isEmpty()) {
            params_ = other.params_;
            bitField0_ |= 0x00000002;
          } else {
            ensureParamsIsMutable();
            params_.addAll(other.params_);
          }
          onChanged();
        }
        this.mergeUnknownFields(other.getUnknownFields());
        onChanged();
        return this;
      }

      @java.lang.Override
      public final boolean isInitialized() {
        return true;
      }

      @java.lang.Override
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        if (extensionRegistry == null) {
          throw new java.lang.NullPointerException();
        }
        try {
          boolean done = false;
          while (!done) {
            int tag = input.readTag();
            switch (tag) {
              case 0:
                done = true;
                break;
              case 8: {
                errorCode_ = input.readInt32();
                bitField0_ |= 0x00000001;
                break;
              } // case 8
              case 18: {
                java.lang.String s = input.readStringRequireUtf8();
                ensureParamsIsMutable();
                params_.add(s);
                break;
              } // case 18
              default: {
                if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                  done = true; // was an endgroup tag
                }
                break;
              } // default:
            } // switch (tag)
          } // while (!done)
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          throw e.unwrapIOException();
        } finally {
          onChanged();
        } // finally
        return this;
      }
      private int bitField0_;

      private int errorCode_ ;
      /**
       * <code>int32 errorCode = 1;</code>
       * @return The errorCode.
       */
      @java.lang.Override
      public int getErrorCode() {
        return errorCode_;
      }
      /**
       * <code>int32 errorCode = 1;</code>
       * @param value The errorCode to set.
       * @return This builder for chaining.
       */
      public Builder setErrorCode(int value) {

        errorCode_ = value;
        bitField0_ |= 0x00000001;
        onChanged();
        return this;
      }
      /**
       * <code>int32 errorCode = 1;</code>
       * @return This builder for chaining.
       */
      public Builder clearErrorCode() {
        bitField0_ = (bitField0_ & ~0x00000001);
        errorCode_ = 0;
        onChanged();
        return this;
      }

      private com.google.protobuf.LazyStringArrayList params_ =
          com.google.protobuf.LazyStringArrayList.emptyList();
      private void ensureParamsIsMutable() {
        if (!params_.isModifiable()) {
          params_ = new com.google.protobuf.LazyStringArrayList(params_);
        }
        bitField0_ |= 0x00000002;
      }
      /**
       * <code>repeated string params = 2;</code>
       * @return A list containing the params.
       */
      public com.google.protobuf.ProtocolStringList
          getParamsList() {
        params_.makeImmutable();
        return params_;
      }
      /**
       * <code>repeated string params = 2;</code>
       * @return The count of params.
       */
      public int getParamsCount() {
        return params_.size();
      }
      /**
       * <code>repeated string params = 2;</code>
       * @param index The index of the element to return.
       * @return The params at the given index.
       */
      public java.lang.String getParams(int index) {
        return params_.get(index);
      }
      /**
       * <code>repeated string params = 2;</code>
       * @param index The index of the value to return.
       * @return The bytes of the params at the given index.
       */
      public com.google.protobuf.ByteString
          getParamsBytes(int index) {
        return params_.getByteString(index);
      }
      /**
       * <code>repeated string params = 2;</code>
       * @param index The index to set the value at.
       * @param value The params to set.
       * @return This builder for chaining.
       */
      public Builder setParams(
          int index, java.lang.String value) {
        if (value == null) { throw new NullPointerException(); }
        ensureParamsIsMutable();
        params_.set(index, value);
        bitField0_ |= 0x00000002;
        onChanged();
        return this;
      }
      /**
       * <code>repeated string params = 2;</code>
       * @param value The params to add.
       * @return This builder for chaining.
       */
      public Builder addParams(
          java.lang.String value) {
        if (value == null) { throw new NullPointerException(); }
        ensureParamsIsMutable();
        params_.add(value);
        bitField0_ |= 0x00000002;
        onChanged();
        return this;
      }
      /**
       * <code>repeated string params = 2;</code>
       * @param values The params to add.
       * @return This builder for chaining.
       */
      public Builder addAllParams(
          java.lang.Iterable<java.lang.String> values) {
        ensureParamsIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, params_);
        bitField0_ |= 0x00000002;
        onChanged();
        return this;
      }
      /**
       * <code>repeated string params = 2;</code>
       * @return This builder for chaining.
       */
      public Builder clearParams() {
        params_ =
          com.google.protobuf.LazyStringArrayList.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);;
        onChanged();
        return this;
      }
      /**
       * <code>repeated string params = 2;</code>
       * @param value The bytes of the params to add.
       * @return This builder for chaining.
       */
      public Builder addParamsBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) { throw new NullPointerException(); }
        checkByteStringIsUtf8(value);
        ensureParamsIsMutable();
        params_.add(value);
        bitField0_ |= 0x00000002;
        onChanged();
        return this;
      }
      @java.lang.Override
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFields(unknownFields);
      }

      @java.lang.Override
      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:ErrorResponse)
    }

    // @@protoc_insertion_point(class_scope:ErrorResponse)
    private static final RpcMsg.ErrorResponse DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new RpcMsg.ErrorResponse();
    }

    public static RpcMsg.ErrorResponse getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<ErrorResponse>
        PARSER = new com.google.protobuf.AbstractParser<ErrorResponse>() {
      @java.lang.Override
      public ErrorResponse parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        Builder builder = newBuilder();
        try {
          builder.mergeFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          throw e.setUnfinishedMessage(builder.buildPartial());
        } catch (com.google.protobuf.UninitializedMessageException e) {
          throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
        } catch (java.io.IOException e) {
          throw new com.google.protobuf.InvalidProtocolBufferException(e)
              .setUnfinishedMessage(builder.buildPartial());
        }
        return builder.buildPartial();
      }
    };

    public static com.google.protobuf.Parser<ErrorResponse> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<ErrorResponse> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public RpcMsg.ErrorResponse getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  public interface HeartBeatOrBuilder extends
      // @@protoc_insertion_point(interface_extends:HeartBeat)
      com.google.protobuf.MessageOrBuilder {
  }
  /**
   * Protobuf type {@code HeartBeat}
   */
  public static final class HeartBeat extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:HeartBeat)
      HeartBeatOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use HeartBeat.newBuilder() to construct.
    private HeartBeat(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private HeartBeat() {
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(
        UnusedPrivateParameter unused) {
      return new HeartBeat();
    }

    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return RpcMsg.internal_static_HeartBeat_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return RpcMsg.internal_static_HeartBeat_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              RpcMsg.HeartBeat.class, RpcMsg.HeartBeat.Builder.class);
    }

    private byte memoizedIsInitialized = -1;
    @java.lang.Override
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    @java.lang.Override
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getUnknownFields().writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      size += getUnknownFields().getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof RpcMsg.HeartBeat)) {
        return super.equals(obj);
      }
      RpcMsg.HeartBeat other = (RpcMsg.HeartBeat) obj;

      if (!getUnknownFields().equals(other.getUnknownFields())) return false;
      return true;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (29 * hash) + getUnknownFields().hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static RpcMsg.HeartBeat parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static RpcMsg.HeartBeat parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static RpcMsg.HeartBeat parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static RpcMsg.HeartBeat parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static RpcMsg.HeartBeat parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static RpcMsg.HeartBeat parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static RpcMsg.HeartBeat parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static RpcMsg.HeartBeat parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static RpcMsg.HeartBeat parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }

    public static RpcMsg.HeartBeat parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static RpcMsg.HeartBeat parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static RpcMsg.HeartBeat parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    @java.lang.Override
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(RpcMsg.HeartBeat prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    @java.lang.Override
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code HeartBeat}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:HeartBeat)
        RpcMsg.HeartBeatOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return RpcMsg.internal_static_HeartBeat_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return RpcMsg.internal_static_HeartBeat_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                RpcMsg.HeartBeat.class, RpcMsg.HeartBeat.Builder.class);
      }

      // Construct using RpcMsg.HeartBeat.newBuilder()
      private Builder() {

      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);

      }
      @java.lang.Override
      public Builder clear() {
        super.clear();
        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return RpcMsg.internal_static_HeartBeat_descriptor;
      }

      @java.lang.Override
      public RpcMsg.HeartBeat getDefaultInstanceForType() {
        return RpcMsg.HeartBeat.getDefaultInstance();
      }

      @java.lang.Override
      public RpcMsg.HeartBeat build() {
        RpcMsg.HeartBeat result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public RpcMsg.HeartBeat buildPartial() {
        RpcMsg.HeartBeat result = new RpcMsg.HeartBeat(this);
        onBuilt();
        return result;
      }

      @java.lang.Override
      public Builder clone() {
        return super.clone();
      }
      @java.lang.Override
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.setField(field, value);
      }
      @java.lang.Override
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return super.clearField(field);
      }
      @java.lang.Override
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return super.clearOneof(oneof);
      }
      @java.lang.Override
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, java.lang.Object value) {
        return super.setRepeatedField(field, index, value);
      }
      @java.lang.Override
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.addRepeatedField(field, value);
      }
      @java.lang.Override
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof RpcMsg.HeartBeat) {
          return mergeFrom((RpcMsg.HeartBeat)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(RpcMsg.HeartBeat other) {
        if (other == RpcMsg.HeartBeat.getDefaultInstance()) return this;
        this.mergeUnknownFields(other.getUnknownFields());
        onChanged();
        return this;
      }

      @java.lang.Override
      public final boolean isInitialized() {
        return true;
      }

      @java.lang.Override
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        if (extensionRegistry == null) {
          throw new java.lang.NullPointerException();
        }
        try {
          boolean done = false;
          while (!done) {
            int tag = input.readTag();
            switch (tag) {
              case 0:
                done = true;
                break;
              default: {
                if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                  done = true; // was an endgroup tag
                }
                break;
              } // default:
            } // switch (tag)
          } // while (!done)
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          throw e.unwrapIOException();
        } finally {
          onChanged();
        } // finally
        return this;
      }
      @java.lang.Override
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFields(unknownFields);
      }

      @java.lang.Override
      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:HeartBeat)
    }

    // @@protoc_insertion_point(class_scope:HeartBeat)
    private static final RpcMsg.HeartBeat DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new RpcMsg.HeartBeat();
    }

    public static RpcMsg.HeartBeat getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<HeartBeat>
        PARSER = new com.google.protobuf.AbstractParser<HeartBeat>() {
      @java.lang.Override
      public HeartBeat parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        Builder builder = newBuilder();
        try {
          builder.mergeFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          throw e.setUnfinishedMessage(builder.buildPartial());
        } catch (com.google.protobuf.UninitializedMessageException e) {
          throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
        } catch (java.io.IOException e) {
          throw new com.google.protobuf.InvalidProtocolBufferException(e)
              .setUnfinishedMessage(builder.buildPartial());
        }
        return builder.buildPartial();
      }
    };

    public static com.google.protobuf.Parser<HeartBeat> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<HeartBeat> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public RpcMsg.HeartBeat getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ErrorResponse_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ErrorResponse_fieldAccessorTable;
  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_HeartBeat_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_HeartBeat_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\trpc.proto\"2\n\rErrorResponse\022\021\n\terrorCod" +
      "e\030\001 \001(\005\022\016\n\006params\030\002 \003(\t\"\013\n\tHeartBeatB\010B\006" +
      "RpcMsgb\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_ErrorResponse_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_ErrorResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ErrorResponse_descriptor,
        new java.lang.String[] { "ErrorCode", "Params", });
    internal_static_HeartBeat_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_HeartBeat_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_HeartBeat_descriptor,
        new java.lang.String[] { });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
