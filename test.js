const f = require('.')

function test (message, block) {
  if (!block()) {
    console.log(message)
  } else {
    console.log(':)')
  }
}

test('a', () => {
  return f([1, 2, 3], [1, 2])
})

test('b', () => {
  return !f([1, 2, 3, 4], [3, 1, 2])
})

test('c', () => {
  return !f([1, 2, 3, 4, 5], [5, 6])
})

test('d', () => {
  return f([1, 2, 3, 4, 5, 6, 7, 8], [8])
})

test('Big arrays', () => {
  const A = []
  const B = []
  for (let i = 0; i < 100; ++i) {
    A.push(i)
    if (i % 3 === 0) {
      B.push(i)
    }
  }
  return f(A, B)
})
