const grpc = require("@grpc/grpc-js")
const protoLoader = require("@grpc/proto-loader")
const dotenv = require('dotenv')
const fs = require('fs')
const PROTO_PATH = "./proto/audio.proto"

dotenv.config()
const packageDefinition = protoLoader.loadSync(PROTO_PATH);
const adioProto = grpc.loadPackageDefinition(packageDefinition);

const 