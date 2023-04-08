import request from "../utils/request";

export default async function(dto){
    return await request.post("/user",dto)
}