const url = "" + response.headers.valueOf("Location");
client.log("url: " + url)

const roomId = url.replace(/.*\/(?=\d+$)/, "");
client.log("room_id: " + roomId)

// Save room id for following requests
client.global.set("room_id", roomId);
