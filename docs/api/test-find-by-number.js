client.test("Request executed successfully", () => {
    client.assert(response.status === 200, "Response status is not 200");

    const type = response.contentType.mimeType;
    client.assert(type === "application/hal+json", "Expected 'application/hal+json' but received '" + type + "'");

    // const {body} = response.body;
    // client.log("Hello");
    // console.log("Hello");
    // client.global.set("room_id", 27);
});