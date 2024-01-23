<script>
	import Counter from './Counter.svelte';
	import BatchOrders from './BatchOrders.svelte';
	import temporal from '$lib/images/temporal_logo.png';
</script>

<svelte:head>
	<title>Home</title>
	<meta name="description" content="Temporal - Process Orders" />
</svelte:head>

<section>
	<h1>
		Process Orders
	</h1>

    <BatchOrders />
</section>

<style>
	section {
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
		flex: 0.6;
	}

	h1 {
		width: 100%;
	}

</style>
