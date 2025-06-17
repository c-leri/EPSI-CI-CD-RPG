<script lang="ts">
	import '../app.css';
    import Dashboard from "$lib/Dashboard/Dashboard.svelte";
	let { children } = $props();

</script>

<Dashboard />
{@render children()}
