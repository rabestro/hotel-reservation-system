client.test("Should return status 409 (Conflict)", () => {
    client.assert(response.status === 409, "Response status is not 409");

    const type = response.contentType.mimeType;
    client.assert(type === "application/json", "Expected 'application/json' but received '" + type + "'");

    // client.log("Look, I can log!");
    // client.assert(response.body.message.startsWith("could not execute statement"));
});
