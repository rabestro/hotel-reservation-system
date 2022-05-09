client.test("Request executed successfully", () =>
    client.assert(response.status === 201, "Expected response status 201 but received '" + response.status + "'")
);
const expected = "text/plain";
client.test(`Response content-type is ${expected}`, () => {
    const type = response.contentType.mimeType;
    client.assert(type === expected, `Expected '${expected}' but received '${type}'`);
});

const url = "" + response.headers.valueOf("Location");
const roomId = url.replace(/.*\/(?=\d+$)/, "");
client.log(`room id#${roomId} successfully added`);

// Save last room id for following requests
client.global.set("last_room_id", roomId);

