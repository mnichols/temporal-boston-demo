<script lang="ts">

    // Example POST method implementation:
    async function postData(url = "", data = {}) {
      // Default options are marked with *
      const response = await fetch(url, {
        method: "POST", // *GET, POST, PUT, DELETE, etc.
        mode: "cors", // no-cors, *cors, same-origin
        cache: "no-cache", // *default, no-cache, reload, force-cache, only-if-cached
        credentials: "same-origin", // include, *same-origin, omit
        headers: {
          "Content-Type": "application/json",
          // 'Content-Type': 'application/x-www-form-urlencoded',
        },
        redirect: "follow", // manual, *follow, error
        referrerPolicy: "no-referrer", // no-referrer, *no-referrer-when-downgrade, origin, origin-when-cross-origin, same-origin, strict-origin, strict-origin-when-cross-origin, unsafe-url
        body: JSON.stringify(data), // body data type must match "Content-Type" header
      });
      return response.json(); // parses JSON response into native JavaScript objects
    }

	let count = 1;
	let response = 'ready';
    async function doBatchOrders() {
        response = 'working...';
        response = await postData('http://localhost:8081/orders', { count })
    }

</script>
<div>
    <label for="count" style='font-size: 30px;'>
        <span style='font-size: inherit;'>How many orders to process?</span>
        <input name="count" type="number" min="1" max="10000" bind:value={count} />
    </label>
    <button type='button' style='background-color: green; color: white;' on:click={ doBatchOrders }>Submit</button>
    <div style='font-weight:800; font-size:28px; color: blue;'>{ response }</div>
</div>
