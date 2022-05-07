client.test("Request executed successfully", () =>
    client.assert(response.status === 201, "Expected response status 201 but received '" + response.status + "'")
);

client.test("Response content-type is text/plain", () => {
    const type = response.contentType.mimeType;
    client.assert(type === "text/plain", "Expected 'application/hal+json' but received '" + type + "'");
});

client.log("a new room successfully added");

const url = "" + response.headers.valueOf("Location");
client.log("url: " + url);

const roomId = url.replace(/.*\/(?=\d+$)/, "");
client.log("room_id: " + roomId);

// Save room id for following requests
client.global.set("room_id", roomId);

