client.test("Request executed successfully", () =>
    client.assert(response.status === 201, "Expected response status 201 but received '" + response.status + "'")
);

client.test("Response content-type is text/plain", () => {
    const type = response.contentType.mimeType;
    client.assert(type === "text/plain", "Expected 'application/hal+json' but received '" + type + "'");
});

client.log("a new room type successfully added");
