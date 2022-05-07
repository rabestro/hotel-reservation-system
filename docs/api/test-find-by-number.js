// Test search by room number request

client.test("Request executed successfully", () => {
    client.assert(response.status === 200, "Response status is not 200");

    const type = response.contentType.mimeType;
    client.assert(type === "application/hal+json", "Expected 'application/hal+json' but received '" + type + "'");

    const body = response.body;

    // Save pantry room id for following requests
    const roomId = body._links.self.href.replace(/.*\/(?=\d+$)/, '');
    client.log("room_id: " + roomId)
    client.global.set("room_id", roomId);
});
