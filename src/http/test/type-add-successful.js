client.test("Request executed successfully", () =>
    client.assert(response.status === 201, "Expected response status 201 but received '" + response.status + "'")
);

client.test("Response content-type is application/json", () => {
    const type = response.contentType.mimeType;
    client.assert(type === "application/json", "Expected 'application/json' but received '" + type + "'");
});

client.log("a new room type successfully added");

const url = "" + response.headers.valueOf("Location");
client.log("url: " + url);

const typeId = url.replace(/.*\/(?=\d+$)/, '');
const typeName = response.body["name"].replace(/ /g, '')

client.log(`type_id=${typeId}, name=${typeName}`);

// client.log("Type name: " + typeName)

// Save room id for following requests
client.global.set(typeName, typeId);
